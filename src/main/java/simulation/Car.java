package simulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

class Car extends TrafficParticipant {

    private int acceleration;
    private int downturn;
    private int maxSpeed;
    private int speed = 0;
    private int driverBehavior; // od -10% do 10%
    private int distance = 0;
    private Point previousTurningPoint; //TODO statistics
    private Point turningPoint;
    private Crossroad crossroad;
    private boolean isChangingLine = false, isOnCrossroad, isLettingCarOnCrossroad = false, isInTrafficAccident = false;
    private Car carToGoFirst;
    private int cycleCount;
    private List<Integer> carsOnRoad = new ArrayList<>();
    private int waitingTime = 0, waitingTimeOnCollision = 0;

    private Random random = new Random();



    Car(String name, Point startingPoint, Point endingPoint, boolean isSafe, int acceleration) throws Exception {
        super(name, isSafe, isSafe ? "car.png" : "car_not_safe.png");
        this.acceleration = acceleration;
        this.downturn = 2 * acceleration;
        this.driverBehavior = random.nextInt(20) - 10;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        generateRoute();
        this.road = route.get(0).getRoad();
        this.line = getStartingLine(road);
        this.crossroad = line.getNextCrossroad();
        this.maxSpeed = this.road.getMaxSpeed() + (this.road.getMaxSpeed() * this.driverBehavior / 100);
        route.remove(0);
        this.position = setStartingPosition();
        onRoadChange();
        imageOrientation();
        setImagePosition();
    }

    private Line getStartingLine(Road road) throws Exception {
        for (Line line : road.getLines()) {
            if (line.getStart().equals(startingPoint) || line.getEnd().equals(startingPoint))
                return line;
            else if (line.getTrafficMovement().equals(route.get(0).getDirection()))
                return line;
        }
        throw new Exception("Cannot generate starting road");
    }

    private Point setStartingPosition() {
        if (startingPoint.equals(line.getEnd()) || startingPoint.equals(line.getStart()))
            return startingPoint;
        else if (line.isVertical())
            return new Point(line.getEnd().x, startingPoint.y);
        else
            return new Point(startingPoint.x, line.getEnd().y);
    }

    void setImagePosition() {
        if ((int) trafficParticipantImageView.getRotate() == 0 || (int) trafficParticipantImageView.getRotate() == 180) {
            trafficParticipantImageView.setX(position.x - 5);
            trafficParticipantImageView.setY(position.y - 3);
        } else {
            trafficParticipantImageView.setX(position.x - 3);
            trafficParticipantImageView.setY(position.y - 5);
        }
    }

    void correctSpeed() {
        try {
            if (speed < maxSpeed && !isTooCloseToCar() && !isStoppingOnRedLight() && carToGoFirst == null
                    && !isPedestrianOnCrossing() && !isCarOnCourseOnCrossroad()
                    && !(isLettingCarOnCrossroad = isLettingCarsOnCrossroad()) && !isInTrafficAccident)
                accelerate();
            else
                slowDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isLettingCarsOnCrossroad() throws Exception {
        if (road.getType().equals("2way") && route.size() > 0 && checkDistanceToCrossRoad() > 0 && ignoreTraffic != IGNORE_PRIORITY) {
            if (isOnCrossroad && distanceFromPoint(turningPoint, this) > 0
                    && distanceFromPoint(turningPoint, this) < 30) {
                if (crossroad.getCars().size() > 1) {
                    switch (numberOfRoadsWithPriority()) {
                        case 0:
                            return false;
                        case 1:
                            return isLettingCars(directionToCheck(1));
                        case 2:
                            if (isLettingCars(directionToCheck(1)))
                                return true;
                            else
                                return isLettingCars(directionToCheck(2));
                    }
                }
            }
        }
        return false;
    }

    private boolean isLettingCars(String direction) throws Exception {
        for (Car car : crossroad.getCars()) {
            if (!car.equals(this))
                if (car.distanceFromPoint(car.turningPoint, car) < 30 && car.distanceFromPoint(car.turningPoint, car) > 0
                        && !car.isLettingCarOnCrossroad()) {
                    if (car.getLine().getTrafficMovement().equals(direction))
                        if (car.getLine().getStreetLights() != null) {
                            if (car.getLine().getStreetLights().getLight() != StreetLights.RED)
                                return true;
                        } else
                            return true;
                }
        }
        return false;
    }

    private String directionToCheck(int i) throws Exception {
        int currentDirection = getDirectionInt(line.getTrafficMovement());
        return getDirectionString((currentDirection - i) % 4);

    }

    private String getDirectionString(int i) throws Exception {
        switch (i) {
            case -3:
                return "N";
            case -2:
                return "E";
            case -1:
                return "S";
            case 0:
                return "W";
        }
        throw new Exception("Wrong direction int!");
    }

    private int getDirectionInt(String direction) throws Exception {
        switch (direction) {
            case "N":
                return -3;
            case "E":
                return -2;
            case "S":
                return -1;
            case "W":
                return 0;
        }
        throw new Exception("Wrong direction!");
    }

    private int numberOfRoadsWithPriority() {
        switch (line.getTrafficMovement()) {
            case "N":
                if (route.get(0).getDirection().equals("E"))
                    return 0;
                else if (route.get(0).getDirection().equals("N"))
                    return 1;
                else
                    return 2;
            case "E":
                if (route.get(0).getDirection().equals("S"))
                    return 0;
                else if (route.get(0).getDirection().equals("E"))
                    return 1;
                else
                    return 2;
            case "S":
                if (route.get(0).getDirection().equals("W"))
                    return 0;
                else if (route.get(0).getDirection().equals("S"))
                    return 1;
                else
                    return 2;
            case "W":
                if (route.get(0).getDirection().equals("N"))
                    return 0;
                else if (route.get(0).getDirection().equals("W"))
                    return 1;
                else
                    return 2;
        }
        return -1;
    }

    private boolean isCarOnCourseOnCrossroad() {
        if (isOnCrossroad && crossroad.getCars().size() > 1) {
            for (Car car : crossroad.getCars()) {
                if (!car.equals(this)) {
                    if (isCarOnCourse(car))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean isCarOnCourse(Car car) {
        if (isCarOnCollisionCourse(car, line.isVertical())) {
            try {
                if(isCarInFrontInRange(car)){
                    if(isBumpingIntoCar(car)){
                        StatisticsSaver statisticsSaver = new StatisticsSaver(new StatisticsElement(road.getName(),
                                line.getTrafficMovement()));
                        setInTrafficAccident(true);
                        car.setInTrafficAccident(true);
                    }
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    private boolean isCarInFrontInRange(Car car) throws Exception {
        switch (line.getTrafficMovement()) {
            case "N":
                return car.position.y - this.position.y < 0 && car.position.y - this.position.y > -25;
            case "E":
                return car.position.x - this.position.x > 0 && car.position.x - this.position.x < 25;
            case "S":
                return car.position.y - this.position.y > 0 && car.position.y - this.position.y < 25;
            case "W":
                return car.position.x - this.position.x < 0 && car.position.x - this.position.x > -25;
        }
        throw new Exception("Wrong movement direction!");
    }

    private boolean isCarOnCollisionCourse(Car car, boolean isVertical) {
        if (isVertical)
            return Math.abs(position.x - car.getPosition().x) < 9;
        else
            return Math.abs(position.y - car.getPosition().y) < 9;
    }

    private boolean isPedestrianOnCrossing() {
        if (road.getPedestrianCrossings() != null && ignoreTraffic != IGNORE_PEDESTRIANS)
            for (PedestrianCrossing pedestrianCrossing : road.getPedestrianCrossings()) {
                if (isPedestrianCrossingInFront(pedestrianCrossing)) {
                    if (pedestrianCrossing.getPedestrians().size() > 0) {
                        for (Pedestrian pedestrian : pedestrianCrossing.getPedestrians()) {
                            switch (line.getTrafficMovement()) {
                                case "N":
                                case "S":
                                    if (Math.abs(pedestrian.getPosition().x - position.x) < 15)
                                        return true;
                                    break;
                                case "W":
                                case "E":
                                    if (Math.abs(pedestrian.getPosition().y - position.y) < 15)
                                        return true;
                                    break;
                            }
                        }
                    } else
                        return false;
                }
            }
        return false;
    }

    private boolean isPedestrianCrossingInFront(PedestrianCrossing pedestrianCrossing) {
        switch (line.getTrafficMovement()) {
            case "N":
                return position.y - pedestrianCrossing.getPosition().y <= 25 && position.y - pedestrianCrossing.getPosition().y > 0;
            case "E":
                return pedestrianCrossing.getPosition().x - position.x <= 25 && pedestrianCrossing.getPosition().x - position.x > 0;
            case "S":
                return pedestrianCrossing.getPosition().y - position.y <= 25 && pedestrianCrossing.getPosition().y - position.y > 0;
            case "W":
                return position.x - pedestrianCrossing.getPosition().x <= 25 && position.x - pedestrianCrossing.getPosition().x > 0;
            default:
                return false;
        }

    }

    private boolean isStoppingOnRedLight() {
        if (line.getStreetLights() != null && ignoreTraffic != IGNORE_LIGHTS) {
            if (line.getStreetLights().getLight() == StreetLights.RED || line.getStreetLights().getLight() == StreetLights.YELLOW) {
                return (checkDistanceToCrossRoad() < 35 && checkDistanceToCrossRoad() > 22)
                        || (checkDistanceToCrossRoad() < 10 && checkDistanceToCrossRoad() > 3);
            }
        }
        return false;
    }

    private boolean isTooCloseToCar() throws Exception {
        switch (line.getTrafficMovement()) {
            case "N":
            case "S":
                return isCarInRange(true);
            case "E":
            case "W":
                return isCarInRange(false);
        }
        throw new Exception("Wrong direction");
    }

    private boolean isCarInRange(boolean isVertical) throws Exception {
        for (Car car : line.getCars()) {
            if (!car.equals(this)) {
                if (isCarInFront(car))
                    if (isVertical) {
                        if (isInRange(car.position.y, isVertical)) {
                            if(isBumpingIntoCar(car)) {
                                StatisticsSaver statisticsSaver = new StatisticsSaver(new StatisticsElement(road.getName(),
                                        line.getTrafficMovement()));
                                setInTrafficAccident(true);
                                car.setInTrafficAccident(true);
                            }
                            return true;
                        }
                    } else if (isInRange(car.position.x, isVertical))
                        return true;
            }
        }
        return false;
    }

    private boolean isBumpingIntoCar(Car car) throws Exception{
        int distanceToBump = getDistanceToBump(car);
        if(line.isVertical()){
            return Math.abs(position.y - car.getPosition().y) < distanceToBump;
        }else{
            return Math.abs(position.x - car.getPosition().x) < distanceToBump;
        }
    }

    private int getDistanceToBump(Car car) throws Exception {
        if ((getDirectionInt(this.getLine().getTrafficMovement()) + getDirectionInt(car.getLine().getTrafficMovement()) % 2 == 0))
            return 10;
        else
            return 8;
    }

    private boolean isCarInFront(Car car) throws Exception {
        switch (line.getTrafficMovement()) {
            case "N":
                return car.position.y < this.position.y;
            case "E":
                return car.position.x > this.position.x;
            case "S":
                return car.position.y > this.position.y;
            case "W":
                return car.position.x < this.position.x;
        }
        throw new Exception("Wrong direction");
    }

    private void accelerate() { // 0-20 -> x3, 20-40 -> x2, 40+ -> x1
        if (speed < 20) speed += 3 * acceleration;
        else if (speed < 40) speed += 2 * acceleration;
        else speed += acceleration;
    }

    private void slowDown() {
        if (speed != 0) {
            speed -= downturn;
            if (speed < 0) {
                speed = 0;
                waitingTime++;
                if(line.isClosed())
                    waitingTimeOnCollision++;
            }
        }
    }

    void move() {
        if (Controller.getCycleCounter() % 20 == 0) {
            if (road.getType().equals("1way")) {
                carsOnRoad.add(road.getLines().get(0).getCars().size() + road.getLines().get(1).getCars().size());
            } else
                carsOnRoad.add(line.getCars().size());
        }
        changePosition();
        tryAddingCarToCrossroad();
        setChangingLineCarToGoFirst();
        tryRemovingCarFromCrossroad();
        tryChangingLine();
        tryRemovingCarToGoFirst();
        checkIfTurningOrEndingPointIsReached();
    }

    private void checkIfTurningOrEndingPointIsReached() {
        try {
            if (route.size() > 0) {
                if (!isChangingLine) {
                    if (distanceFromPoint(turningPoint, this) <= 0) {
                        Road road = route.get(0).getRoad();
                        route.remove(0);
                        setRoadAndLine(road, nextLine);
                        imageOrientation();
                        correctPositionPoint();
                    }
                }
            } else if (distanceFromPoint(endingPoint, this) <= 0) {
                setEndReached(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tryRemovingCarToGoFirst() {
        if (carToGoFirst != null) {
            try {
                if (!carToGoFirst.isChangingLine || isCarInFrontInRange(carToGoFirst))
                    carToGoFirst = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void tryChangingLine() {
        if (checkDistanceFromCrossroad() > 30) {
            if(isChangingLine) {
                Line line = road.getLines().get(0).equals(this.line) ? road.getLines().get(1) : road.getLines().get(0);
                if (canEnterLine(line)) {
                    line.getCars().stream().filter(car -> {
                        if (car.getCarToGoFirst() != null)
                            return car.getCarToGoFirst().equals(this);
                        return false;
                    }).forEach(car -> car.setCarToGoFirst(null));
                    change();
                } else {
                    boolean isVertical = line.isVertical();
                    List<Car> carList = line.getCars().stream().filter(car -> isInRange(isVertical ? position.y : position.x, isVertical))
                            .filter(car -> {
                                if (car.getCarToGoFirst() != null)
                                    return car.getCarToGoFirst().equals(this);
                                return false;
                            }).collect(Collectors.toList());
                    if (carList.size() > 0) {
                        line.getCars().stream().filter(car -> {
                            if (car.getCarToGoFirst() != null)
                                return car.getCarToGoFirst().equals(this);
                            return false;
                        }).forEach(car -> car.setCarToGoFirst(null));
                        change();
                    }
                }
            } else {
                if (route.size() > 0) {
                    if (!crossroad.equals(line.getNextCrossroad())) {
                        crossroad.getCars().remove(this);
                        crossroad = line.getNextCrossroad();
                    }
                }else {
                    crossroad.getCars().remove(this);
                }
            }
        }
    }

    private void tryRemovingCarFromCrossroad() {
        if (crossroad != null)
            if (crossroad.getCars().contains(this)) {
                if (checkDistanceFromCrossroad() > 5 && checkDistanceFromCrossroad() < 30
                        && checkDistanceFromCrossroad() < line.getLineLenght() - 5)
                    crossroad.removeCar(this);
            }
    }

    private void setChangingLineCarToGoFirst() {
        if (road.getType().equals("1way")) {
            boolean isVertical = line.isVertical();
            Line line = road.getLines().get(0).equals(this.line) ? road.getLines().get(1) : road.getLines().get(0);
            line.getCars().stream().filter(car -> isInRange(isVertical ? position.y : position.x, isVertical))
                    .filter(car -> {
                        try {
                            return this.isCarInFront(car);
                        } catch (Exception e) {
                            return false;
                        }
                    }).filter(Car::isChangingLine).limit(1).forEach(this::setCarToGoFirst);
        }
    }

    private void tryAddingCarToCrossroad() {
        if (crossroad != null) {
            if (checkDistanceToCrossRoad() < 10 && !crossroad.getCars().contains(this) && !isEndReached) {
                crossroad.addCar(this);
                setOnCrossroad(true);
            }
        }
    }

    private void changePosition() {
        distance += speed;
        switch (line.getTrafficMovement()) {
            case "N":
                position = new Point(position.x, position.y - distance / 50);
                break;
            case "E":
                position = new Point(position.x + distance / 50, position.y);
                break;
            case "S":
                position = new Point(position.x, position.y + distance / 50);
                break;
            case "W":
                position = new Point(position.x - distance / 50, position.y);
                break;
        }
        distance %= 50;
    }

    private void
    correctPositionPoint() {
        if (line.isVertical()) {
            if (position.x != line.getEnd().x)
                position.x = line.getEnd().x;
        } else if (position.y != line.getEnd().y)
            position.y = line.getEnd().y;
    }

    private int distanceFromPoint(Point point, Car car) throws Exception {
        switch (line.getTrafficMovement()) {
            case "N":
                return car.getPosition().y - point.y;
            case "E":
                return point.x - car.getPosition().x;
            case "S":
                return point.y - car.getPosition().y;
            case "W":
                return car.getPosition().x - point.x;
        }
        throw new Exception("Wrong direction!");
    }

    private void changeLine() {
        if (road.getType().equals("1way")) {
            isChangingLine = true;
        }
    }

    private void change() {
        boolean isVertical = this.position.x == line.getEnd().x;
        Line lineToChange = (road.getLines().get(0).equals(line)) ? road.getLines().get(1) : road.getLines().get(0);
        if (canEnterLine(lineToChange)) {
            isChangingLine = false;
            line.removeCar(this);
            line = lineToChange;
            if (route.size() > 0)
                isLineOk();
            line.addCar(this);
            if (isVertical) {
                position.x = line.getEnd().x;
            } else {
                position.y = line.getEnd().y;
            }
        }
    }

    private void turnBack() {
        if (road.getType().equals("2way")) {
            isChangingLine = true;
        }
    }

    private void checkDistanceToCar() {

    }

    private int checkDistanceToCrossRoad() {
        if (line.isVertical()) {
            int start = position.y;
            int ret = start - (line.getTrafficMovement().equals("N") ? road.getStart().y : road.getEnd().y);
            if (line.getTrafficMovement().equals("N"))
                return ret;
            else
                return -ret;
        } else {
            int start = position.x;
            int ret = start - (line.getTrafficMovement().equals("W") ? road.getStart().x : road.getEnd().x);
            if (line.getTrafficMovement().equals("W"))
                return ret;
            else
                return -ret;
        }

    }

    private int checkDistanceFromCrossroad() {
        return line.getLineLenght() - checkDistanceToCrossRoad();
    }

    private void onRoadChange() {
        if(ignoreTraffic != IGNORE_SPEED)
            maxSpeed = road.getMaxSpeed() + (road.getMaxSpeed() * driverBehavior / 100);
        else
            maxSpeed = road.getMaxSpeed() + random.nextInt(20) + 10;
        if (route.size() > 0) {
            if (!isLineOk()) {
                changeLine();
            }
        } else {
            int correctLineId = getCorrectLineId();
            if (correctLineId >= 0) {
                if (!road.getLines().get(correctLineId).equals(line))
                    changeLine();
            } else if (!road.getLines().get(0).equals(line)) {
                changeLine();
            }
        }
    }

    private int getCorrectLineId() {
        for (Line line : road.getLines()) {
            if (line.getEnd().equals(endingPoint) || line.getStart().equals(endingPoint)) {
                return road.getLines().indexOf(line);
            }
        }
        return -1;
    }

    private boolean isLineOk() {
        for (Line line : line.getNextCrossroad().getHowToGo(line)) {
            if (route.get(0).getRoad().getLines().get(0).equals(line)) {
                setNextLine(line);
                return true;
            }
            if (route.get(0).getRoad().getLines().get(1).equals(line)) {
                setNextLine(line);
                return true;
            }
        }
        return false;
    }

    private void setRoadAndLine(Road road, Line line) throws Exception {
        if (!road.getName().equals("roadES")) {
            int averageQuantityOfCars = countAverageQuantityOfCars();
            if (previousTurningPoint != null) {
                if (route.size() > 0) {
                    StatisticsElement statisticsElement = new StatisticsElement(road.getName(),
                            line.getTrafficMovement(), Controller.getCycleCounter() - cycleCount, waitingTime,
                            waitingTimeOnCollision, averageQuantityOfCars, countDistance());
                    StatisticsSaver statisticsSaver = new StatisticsSaver(statisticsElement);
                }
            }
        }
        updateStatisticsData();
        setOnCrossroad(false);
        if (line != null) {
            this.line.removeCar(this);
        }
        this.road = road;
        this.line = line;
        assert this.line != null;
        this.line.addCar(this);
        onRoadChange();
    }

    private void updateStatisticsData() {
        carsOnRoad.removeAll(carsOnRoad);
        cycleCount = Controller.getCycleCounter();
        waitingTime = 0;
    }

    private int countDistance() {
        if (line.isVertical())
            return Math.abs(turningPoint.y - previousTurningPoint.y);
        else
            return Math.abs(turningPoint.x - previousTurningPoint.x);
    }

    private int countAverageQuantityOfCars() {
        int sum = 0;
        for (int i : carsOnRoad) {
            sum += i;
        }
        if(carsOnRoad.size() > 0)
            return sum / carsOnRoad.size();

        return 0;
    }

    private void setNextLine(Line nextLine) {
        this.nextLine = nextLine;
        setTurningPoint();
    }

    private void setTurningPoint() {
        if (line.isVertical()) {
            if(turningPoint != null)
                previousTurningPoint = new Point(turningPoint.x, turningPoint.y);
            if (route.get(0).getDirection().equals("W") || route.get(0).getDirection().equals("E")) {
                turningPoint = new Point(line.getEnd().x, nextLine.getEnd().y);
            } else {
                turningPoint = new Point(line.getEnd().x, line.getNextCrossroad().getPosition().y);
            }
        } else {
            if (nextLine.isVertical()) {
                turningPoint = new Point(nextLine.getEnd().x, line.getEnd().y);
            } else {
                turningPoint = new Point(line.getNextCrossroad().getPosition().x, line.getEnd().y);
            }
        }
    }

    boolean canEnterLine(Line line) {
        if (line.getCars().size() != 0) {
            boolean canEnterLine = true;
            for (Car car : line.getCars()) {
                if (canEnterLine) {
                    if (line.isVertical()) {
                        if (isInRange(car.getPosition().y, true))
                            canEnterLine = false;
                    } else {
                        if (isInRange(car.position.x, false))
                            canEnterLine = false;
                    }
                }
            }
            return canEnterLine;
        }
        return true;
    }

    private boolean isInRange(int position, boolean isVertical) {
        if (isVertical) {
            return Math.abs(this.position.y - position) < 25;
        } else {
            return Math.abs(this.position.x - position) < 25;
        }
    }

    private void trafficAccidentHandler() {
        int time = 10000;
        line.setClosed(true);
        Controller.setIsAccident(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(time), event -> {
            line.setClosed(false);
            setEndReached(true);
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    Car getCarToGoFirst() {
        return carToGoFirst;
    }

    void setCarToGoFirst(Car carToGoFirst) {
        this.carToGoFirst = carToGoFirst;
    }

    boolean isChangingLine() {
        return isChangingLine;
    }

    void setOnCrossroad(boolean onCrossroad) {
        isOnCrossroad = onCrossroad;
        if (onCrossroad && route.size() > 0) {
            if (line.getTrafficMovement().equals(route.get(0).getDirection()))
                maxSpeed = (int) (maxSpeed * 0.8);
            else if (road.getType().equals("1way"))
                maxSpeed = (int) (maxSpeed * 0.5);
            else
                maxSpeed = (int) (maxSpeed * 0.7);
        }
    }

    public void setCycleCount(int cycleCount) {
        this.cycleCount = cycleCount;
    }

    public boolean isLettingCarOnCrossroad() {
        return isLettingCarOnCrossroad;
    }

    public void setLettingCarOnCrossroad(boolean lettingCarOnCrossroad) {
        isLettingCarOnCrossroad = lettingCarOnCrossroad;
    }

    public boolean isInTrafficAccident() {
        return isInTrafficAccident;
    }

    public void setInTrafficAccident(boolean inTrafficAccident) {
        isInTrafficAccident = inTrafficAccident;
        speed = 0;
        maxSpeed = 0;
        trafficAccidentHandler();
    }

    void setEndReached(boolean isEndReached){
        this.isEndReached = isEndReached;
        if(crossroad != null)
            crossroad.getCars().remove(this);
        line.getCars().remove(this);

    }
}
