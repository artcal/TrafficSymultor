package simulation;

import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class PedestrianCrossing {

    private Point position;
    private StreetLights streetLights;
    private int width;
    private List<Pedestrian> pedestrians;
    private ImageView imageView;
    private boolean isVertical;

    PedestrianCrossing(Point position, StreetLights streetLights, int width) {
        pedestrians = new ArrayList<>();
        this.position = position;
        this.streetLights = streetLights;
        this.width = width;
    }

    void setImageView(){
        if(streetLights != null){
            if(imageView != null) imageView.setImage(streetLights.getImage());
            else {
                imageView = new ImageView(streetLights.getImage());
                if(isVertical){
                    imageView.setRotate(90);
                    imageView.setX(position.x - 3);
                    imageView.setY(position.y - 2);
                }else {
                    imageView.setRotate(0);
                    imageView.setX(position.x - 2);
                    imageView.setY(position.y - 3);
                }
            }
        }
    }

    void addPedestrian(Pedestrian pedestrian){
        pedestrians.add(pedestrian);
    }

    void removePedestrian(Pedestrian pedestrian){
        pedestrians.remove(pedestrian);
    }

    Point getPosition() {
        return position;
    }

    StreetLights getStreetLights() {
        return streetLights;
    }

    int getWidth() {
        return width;
    }

    public List<Pedestrian> getPedestrians() {
        return pedestrians;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
        setImageView();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isVertical() {
        return isVertical;
    }
}
