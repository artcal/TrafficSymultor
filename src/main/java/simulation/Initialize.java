package simulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Initialize {

    private static int lightsLength = 5000;

//    private static RoadSign roadSign1;
//    private static RoadSign roadSign2;
//    private static RoadSign roadSign3;
//    private static RoadSign roadSign4;
//    private static RoadSign roadSign5;
//    private static RoadSign roadSign6;
//    private static RoadSign roadSign7;
//    private static RoadSign roadSign8;
//    private static RoadSign roadSign9;
//
    private static StreetLights streetLights2wayV;
    private static StreetLights streetLights2wayH;
    private static StreetLights streetLights1wayHE;
    private static StreetLights streetLights1wayHW;
    private static StreetLights streetLights1wayHEL;
    private static StreetLights streetLights1wayHWL;
    private static StreetLights streetLights1wayVN;
    private static StreetLights streetLights1wayVS;
    private static StreetLights streetLights1wayVNL;
    private static StreetLights streetLights1wayVSL;
    private static StreetLights streetLights1wayHTo2wayW;
    private static StreetLights streetLights1wayHTo2wayS;
    private static StreetLights streetLights2wayHTo1way;
    private static StreetLights streetLights2wayVTo1way;
    private static StreetLights streetLights1wayVEW;
    private static StreetLights streetLights1wayHE2;
    private static StreetLights streetLights1wayHW2;
    private static StreetLights streetLights1wayHEL2;
    private static StreetLights streetLights1wayHWL2;
    private static StreetLights streetLights2wayV2;

    private StreetLights streetLightsP2wayV;
    private StreetLights streetLightsP2wayH;
    private StreetLights streetLightsP1wayHE;
    private StreetLights streetLightsP1wayHW;
    private StreetLights streetLightsP1wayVN;
    private StreetLights streetLightsP1wayVS;
    private StreetLights streetLightsP1wayHTo2way;
    private StreetLights streetLightsP2wayHTo1way;
    private StreetLights streetLightsP2wayVTo1way;
    private StreetLights streetLightsP1wayVEW;
    private StreetLights streetLightsP1wayH2;
    private StreetLights streetLightsP2wayVN2;
    private StreetLights streetLightsP2wayVS2;

    private static Line lineV1;
    private static Line lineV2;
    private static Line lineV3;
    private static Line lineV4;
    private static Line lineV5;
    private static Line lineV6;
    private static Line lineV7;
    private static Line lineV8;
    private static Line lineV9;
    private static Line lineV10;
    private static Line lineV11;
    private static Line lineV12;
    private static Line lineV13;
    private static Line lineV14;
    private static Line lineV15;
    private static Line lineV16;
    private static Line lineV17;
    private static Line lineV18;
    private static Line lineV19;
    private static Line lineV20;
    private static Line lineV21;
    private static Line lineV22;
    private static Line lineV23;
    private static Line lineV24;
    private static Line lineV25;
    private static Line lineV26;
    private static Line lineV27;
    private static Line lineV28;
    private static Line lineV29;
    private static Line lineV30;
    private static Line lineH1;
    private static Line lineH2;
    private static Line lineH3;
    private static Line lineH4;
    private static Line lineH5;
    private static Line lineH6;
    private static Line lineH7;
    private static Line lineH8;
    private static Line lineH9;
    private static Line lineH10;
    private static Line lineH11;
    private static Line lineH12;
    private static Line lineH13;
    private static Line lineH14;
    private static Line lineH15;
    private static Line lineH16;
    private static Line lineH17;
    private static Line lineH18;
    private static Line lineH19;
    private static Line lineH20;
    private static Line lineH21;
    private static Line lineH22;
    private static Line lineH23;
    private static Line lineH24;
    private static Line lineH25;
    private static Line lineH26;
    private static Line lineH27;
    private static Line lineH28;
    private static Line lineH29;
    private static Line lineH30;
    private static Line lineH31;
    private static Line lineH32;
    private static Line lineH33;
    private static Line lineH34;
    private static Line lineH35;
    private static Line lineH36;
    private static Line lineH37;
    private static Line lineH38;
    private static Line lineES1;
    private static Line lineES2;

    private static Road roadV1;
    private static Road roadV2;
    private static Road roadV3;
    private static Road roadV4;
    private static Road roadV5;
    private static Road roadV6;
    private static Road roadV7;
    private static Road roadV8;
    private static Road roadV9;
    private static Road roadV10;
    private static Road roadV11;
    private static Road roadV12;
    private static Road roadV13;
    private static Road roadV14;
    private static Road roadV15;
    private static Road roadH1;
    private static Road roadH2;
    private static Road roadH3;
    private static Road roadH4;
    private static Road roadH5;
    private static Road roadH6;
    private static Road roadH7;
    private static Road roadH8;
    private static Road roadH9;
    private static Road roadH10;
    private static Road roadH11;
    private static Road roadH12;
    private static Road roadH13;
    private static Road roadH14;
    private static Road roadH15;
    private static Road roadH16;
    private static Road roadH17;
    private static Road roadH18;
    private static Road roadH19;
    private static Road roadES;

    private static PedestrianCrossing pedestrianCrossingH1;
    private static PedestrianCrossing pedestrianCrossingH2;
    private static PedestrianCrossing pedestrianCrossingH3;
    private static PedestrianCrossing pedestrianCrossingH4;
    private static PedestrianCrossing pedestrianCrossingH5;
    private static PedestrianCrossing pedestrianCrossingH6;
    private static PedestrianCrossing pedestrianCrossingH7;
    private static PedestrianCrossing pedestrianCrossingH8;
    private static PedestrianCrossing pedestrianCrossingH9;
    private static PedestrianCrossing pedestrianCrossingH10;
    private static PedestrianCrossing pedestrianCrossingH11;
    private static PedestrianCrossing pedestrianCrossingH12;
    private static PedestrianCrossing pedestrianCrossingH13;
    private static PedestrianCrossing pedestrianCrossingH14;
    private static PedestrianCrossing pedestrianCrossingH15;
    private static PedestrianCrossing pedestrianCrossingH16;
    private static PedestrianCrossing pedestrianCrossingH17;
    private static PedestrianCrossing pedestrianCrossingH18;
    private static PedestrianCrossing pedestrianCrossingH19;
    private static PedestrianCrossing pedestrianCrossingH20;
    private static PedestrianCrossing pedestrianCrossingV1;
    private static PedestrianCrossing pedestrianCrossingV2;
    private static PedestrianCrossing pedestrianCrossingV3;
    private static PedestrianCrossing pedestrianCrossingV4;
    private static PedestrianCrossing pedestrianCrossingV5;
    private static PedestrianCrossing pedestrianCrossingV6;
    private static PedestrianCrossing pedestrianCrossingV7;
    private static PedestrianCrossing pedestrianCrossingV8;
    private static PedestrianCrossing pedestrianCrossingV9;
    private static PedestrianCrossing pedestrianCrossingV10;
    private static PedestrianCrossing pedestrianCrossingV11;
    private static PedestrianCrossing pedestrianCrossingV12;
    private static PedestrianCrossing pedestrianCrossingV13;
    private static PedestrianCrossing pedestrianCrossingV14;
    private static PedestrianCrossing pedestrianCrossingV15;
    private static PedestrianCrossing pedestrianCrossingV16;
    private static PedestrianCrossing pedestrianCrossingV17;
    private static PedestrianCrossing pedestrianCrossingV18;
    private static PedestrianCrossing pedestrianCrossingV19;
    private static PedestrianCrossing pedestrianCrossingV20;
    private static PedestrianCrossing pedestrianCrossingV21;
    private static PedestrianCrossing pedestrianCrossingV22;
    private static PedestrianCrossing pedestrianCrossingV23;
    private static PedestrianCrossing pedestrianCrossingV24;
    private static PedestrianCrossing pedestrianCrossingV25;
    private static PedestrianCrossing pedestrianCrossingV26;

    private static Crossroad crossroad11;
    private static Crossroad crossroad12;
    private static Crossroad crossroad13;
    private static Crossroad crossroad14;
    private static Crossroad crossroad15;
    private static Crossroad crossroad21;
    private static Crossroad crossroad22;
    private static Crossroad crossroad23;
    private static Crossroad crossroad24;
    private static Crossroad crossroad31;
    private static Crossroad crossroad32;
    private static Crossroad crossroad33;
    private static Crossroad crossroad34;
    private static Crossroad crossroad35;

    private static List<Road> roads;
    private static List<StreetLights> streetLights;
    private static List<Line> lines;
    private static List<PedestrianCrossing> pedestrianCrossings;
    private static List<Crossroad> crossroads;

    static void main(String[] args) throws Exception {
        Initialize initialize = new Initialize();
        initialize.initializeRoadSigns();
        initialize.initializeStreetLights();
        initialize.initializeLines();
        initialize.initializePedestrianCrossings();
        initialize.initializeRoads();
        initialize.initializeCrossroads();
        initialize.connectLinesOnCrossroads();
        initialize.addNextCrossroadsToLines();

        initialize.pedestrianCrossingsToList();
        initialize.roadsToList();
        initialize.streetLightsToList();
        initialize.linesToList();
        initialize.crossroadsToList();
    }

    private void crossroadsToList() {
        crossroads = new ArrayList<>();

        crossroads.add(crossroad11);
        crossroads.add(crossroad12);
        crossroads.add(crossroad13);
        crossroads.add(crossroad14);
        crossroads.add(crossroad15);
        crossroads.add(crossroad21);
        crossroads.add(crossroad22);
        crossroads.add(crossroad23);
        crossroads.add(crossroad24);
        crossroads.add(crossroad31);
        crossroads.add(crossroad32);
        crossroads.add(crossroad33);
        crossroads.add(crossroad34);
        crossroads.add(crossroad35);
    }

    private void pedestrianCrossingsToList() {
        pedestrianCrossings = new ArrayList<>();

        pedestrianCrossings.add(pedestrianCrossingH1);
        pedestrianCrossings.add(pedestrianCrossingH2);
        pedestrianCrossings.add(pedestrianCrossingH3);
        pedestrianCrossings.add(pedestrianCrossingH4);
        pedestrianCrossings.add(pedestrianCrossingH5);
        pedestrianCrossings.add(pedestrianCrossingH6);
        pedestrianCrossings.add(pedestrianCrossingH7);
        pedestrianCrossings.add(pedestrianCrossingH8);
        pedestrianCrossings.add(pedestrianCrossingH9);
        pedestrianCrossings.add(pedestrianCrossingH10);
        pedestrianCrossings.add(pedestrianCrossingH11);
        pedestrianCrossings.add(pedestrianCrossingH12);
        pedestrianCrossings.add(pedestrianCrossingH13);
        pedestrianCrossings.add(pedestrianCrossingH14);
        pedestrianCrossings.add(pedestrianCrossingH15);
        pedestrianCrossings.add(pedestrianCrossingH16);
        pedestrianCrossings.add(pedestrianCrossingH17);
        pedestrianCrossings.add(pedestrianCrossingH18);
        pedestrianCrossings.add(pedestrianCrossingH19);
        pedestrianCrossings.add(pedestrianCrossingH20);
        pedestrianCrossings.add(pedestrianCrossingV1);
        pedestrianCrossings.add(pedestrianCrossingV2);
        pedestrianCrossings.add(pedestrianCrossingV3);
        pedestrianCrossings.add(pedestrianCrossingV4);
        pedestrianCrossings.add(pedestrianCrossingV5);
        pedestrianCrossings.add(pedestrianCrossingV6);
        pedestrianCrossings.add(pedestrianCrossingV7);
        pedestrianCrossings.add(pedestrianCrossingV8);
        pedestrianCrossings.add(pedestrianCrossingV9);
        pedestrianCrossings.add(pedestrianCrossingV10);
        pedestrianCrossings.add(pedestrianCrossingV11);
        pedestrianCrossings.add(pedestrianCrossingV12);
        pedestrianCrossings.add(pedestrianCrossingV13);
        pedestrianCrossings.add(pedestrianCrossingV14);
        pedestrianCrossings.add(pedestrianCrossingV15);
        pedestrianCrossings.add(pedestrianCrossingV16);
        pedestrianCrossings.add(pedestrianCrossingV17);
        pedestrianCrossings.add(pedestrianCrossingV18);
        pedestrianCrossings.add(pedestrianCrossingV19);
        pedestrianCrossings.add(pedestrianCrossingV20);
        pedestrianCrossings.add(pedestrianCrossingV21);
        pedestrianCrossings.add(pedestrianCrossingV22);
        pedestrianCrossings.add(pedestrianCrossingV23);
        pedestrianCrossings.add(pedestrianCrossingV24);
        pedestrianCrossings.add(pedestrianCrossingV25);
        pedestrianCrossings.add(pedestrianCrossingV26);

    }

    private void linesToList() {
        lines = new ArrayList<>();
        lines.add(lineV1);
        lines.add(lineV2);
        lines.add(lineV3);
        lines.add(lineV4);
        lines.add(lineV5);
        lines.add(lineV6);
        lines.add(lineV7);
        lines.add(lineV8);
        lines.add(lineV9);
        lines.add(lineV10);
        lines.add(lineV11);
        lines.add(lineV12);
        lines.add(lineV13);
        lines.add(lineV14);
        lines.add(lineV15);
        lines.add(lineV16);
        lines.add(lineV17);
        lines.add(lineV18);
        lines.add(lineV19);
        lines.add(lineV20);
        lines.add(lineV21);
        lines.add(lineV22);
        lines.add(lineV23);
        lines.add(lineV24);
        lines.add(lineV25);
        lines.add(lineV26);
        lines.add(lineV27);
        lines.add(lineV28);
        lines.add(lineV29);
        lines.add(lineV30);
        lines.add(lineH1);
        lines.add(lineH2);
        lines.add(lineH3);
        lines.add(lineH4);
        lines.add(lineH5);
        lines.add(lineH6);
        lines.add(lineH7);
        lines.add(lineH8);
        lines.add(lineH9);
        lines.add(lineH10);
        lines.add(lineH11);
        lines.add(lineH12);
        lines.add(lineH13);
        lines.add(lineH14);
        lines.add(lineH15);
        lines.add(lineH16);
        lines.add(lineH17);
        lines.add(lineH18);
        lines.add(lineH19);
        lines.add(lineH20);
        lines.add(lineH21);
        lines.add(lineH22);
        lines.add(lineH23);
        lines.add(lineH24);
        lines.add(lineH25);
        lines.add(lineH26);
        lines.add(lineH27);
        lines.add(lineH28);
        lines.add(lineH29);
        lines.add(lineH30);
        lines.add(lineH31);
        lines.add(lineH32);
        lines.add(lineH33);
        lines.add(lineH34);
        lines.add(lineH35);
        lines.add(lineH36);
        lines.add(lineH37);
        lines.add(lineH38);
        lines.add(lineES1);
        lines.add(lineES2);
    }

    private void streetLightsToList() {
        streetLights = new ArrayList<>();
        streetLights.add(streetLights2wayV);
        streetLights.add(streetLights2wayH);
        streetLights.add(streetLights2wayHTo1way);
        streetLights.add(streetLights2wayVTo1way);
        streetLights.add(streetLights1wayHE);
        streetLights.add(streetLights1wayHW);
        streetLights.add(streetLights1wayHEL);
        streetLights.add(streetLights1wayHWL);
        streetLights.add(streetLights1wayVN);
        streetLights.add(streetLights1wayVS);
        streetLights.add(streetLights1wayVNL);
        streetLights.add(streetLights1wayVSL);
        streetLights.add(streetLights1wayHTo2wayW);
        streetLights.add(streetLights1wayHTo2wayS);
        streetLights.add(streetLights1wayHE2);
        streetLights.add(streetLights1wayHW2);
        streetLights.add(streetLights1wayHEL2);
        streetLights.add(streetLights1wayHWL2);
        streetLights.add(streetLights2wayV2);

        streetLights.add(streetLightsP2wayV);
        streetLights.add(streetLightsP2wayH);
        streetLights.add(streetLightsP1wayHE);
        streetLights.add(streetLightsP1wayHW);
        streetLights.add(streetLightsP1wayVN);
        streetLights.add(streetLightsP1wayVS);
        streetLights.add(streetLightsP1wayHTo2way);
        streetLights.add(streetLightsP2wayVTo1way);
        streetLights.add(streetLightsP1wayH2);
        streetLights.add(streetLightsP2wayVN2);
        streetLights.add(streetLightsP2wayVS2);
    }

    private void roadsToList() {
        roads = new ArrayList<>();

        roads.add(roadV1);
        roads.add(roadV2);
        roads.add(roadV3);
        roads.add(roadV4);
        roads.add(roadV5);
        roads.add(roadV6);
        roads.add(roadV7);
        roads.add(roadV8);
        roads.add(roadV9);
        roads.add(roadV10);
        roads.add(roadV11);
        roads.add(roadV12);
        roads.add(roadV13);
        roads.add(roadV14);
        roads.add(roadV15);
        roads.add(roadH1);
        roads.add(roadH2);
        roads.add(roadH3);
        roads.add(roadH4);
        roads.add(roadH5);
        roads.add(roadH6);
        roads.add(roadH7);
        roads.add(roadH8);
        roads.add(roadH9);
        roads.add(roadH10);
        roads.add(roadH11);
        roads.add(roadH12);
        roads.add(roadH13);
        roads.add(roadH14);
        roads.add(roadH15);
        roads.add(roadH16);
        roads.add(roadH17);
        roads.add(roadH18);
        roads.add(roadH19);
        roads.add(roadES);
    }

    private void addNextCrossroadsToLines() {
        lineV1.setNextCrossroad(crossroad11);
        lineV2.setNextCrossroad(null);
        lineV3.setNextCrossroad(crossroad13);
        lineV4.setNextCrossroad(null);
        lineV5.setNextCrossroad(crossroad14);
        lineV6.setNextCrossroad(null);
        lineV7.setNextCrossroad(crossroad21);
        lineV8.setNextCrossroad(crossroad11);
        lineV9.setNextCrossroad(crossroad22);
        lineV10.setNextCrossroad(crossroad12);
        lineV11.setNextCrossroad(crossroad35);
        lineV12.setNextCrossroad(crossroad15);
        lineV13.setNextCrossroad(crossroad31);
        lineV14.setNextCrossroad(crossroad21);
        lineV15.setNextCrossroad(crossroad33);
        lineV16.setNextCrossroad(crossroad33);
        lineV17.setNextCrossroad(crossroad23);
        lineV18.setNextCrossroad(crossroad23);
        lineV19.setNextCrossroad(crossroad34);
        lineV20.setNextCrossroad(crossroad24);
        lineV21.setNextCrossroad(null);
        lineV22.setNextCrossroad(crossroad31);
        lineV23.setNextCrossroad(null);
        lineV24.setNextCrossroad(crossroad32);
        lineV25.setNextCrossroad(null);
        lineV26.setNextCrossroad(null);
        lineV27.setNextCrossroad(crossroad33);
        lineV28.setNextCrossroad(crossroad33);
        lineV29.setNextCrossroad(null);
        lineV30.setNextCrossroad(crossroad35);
        lineH1.setNextCrossroad(null);
        lineH2.setNextCrossroad(crossroad11);
        lineH3.setNextCrossroad(crossroad11);
        lineH4.setNextCrossroad(crossroad12);
        lineH5.setNextCrossroad(crossroad12);
        lineH6.setNextCrossroad(crossroad13);
        lineH7.setNextCrossroad(crossroad13);
        lineH8.setNextCrossroad(crossroad14);
        lineH9.setNextCrossroad(crossroad14);
        lineH10.setNextCrossroad(crossroad15);
        lineH11.setNextCrossroad(crossroad15);
        lineH12.setNextCrossroad(null);
        lineH13.setNextCrossroad(crossroad21);
        lineH14.setNextCrossroad(crossroad22);
        lineH15.setNextCrossroad(crossroad22);
        lineH16.setNextCrossroad(crossroad23);
        lineH17.setNextCrossroad(crossroad23);
        lineH18.setNextCrossroad(crossroad24);
        lineH19.setNextCrossroad(null);
        lineH20.setNextCrossroad(crossroad31);
        lineH21.setNextCrossroad(crossroad31);
        lineH22.setNextCrossroad(crossroad32);
        lineH23.setNextCrossroad(crossroad32);
        lineH24.setNextCrossroad(crossroad32);
        lineH25.setNextCrossroad(crossroad33);
        lineH26.setNextCrossroad(crossroad33);
        lineH27.setNextCrossroad(crossroad33);
        lineH28.setNextCrossroad(crossroad33);
        lineH29.setNextCrossroad(crossroad34);
        lineH30.setNextCrossroad(crossroad34);
        lineH31.setNextCrossroad(crossroad34);
        lineH32.setNextCrossroad(crossroad34);
        lineH33.setNextCrossroad(crossroad35);
        lineH34.setNextCrossroad(crossroad35);
        lineH35.setNextCrossroad(crossroad35);
        lineH36.setNextCrossroad(crossroad35);
        lineH37.setNextCrossroad(null);
        lineH38.setNextCrossroad(null);
        lineES1.setNextCrossroad(crossroad24);
        lineES2.setNextCrossroad(null);
    }

    private void connectLinesOnCrossroads(){ // connect a line to another lines after crossroad
        crossroad11.setEntrances(new Line[] {lineH2, lineH3, lineV1, lineV8});
        crossroad11.setExits(new Line[] {lineH1, lineH4, lineV2, lineV7});
        crossroad11.addHowToGo(lineH2, new Line[] {lineH4, lineV2, lineV7});
        crossroad11.addHowToGo(lineH3, new Line[] {lineH1, lineV2, lineV7});
        crossroad11.addHowToGo(lineV1, new Line[] {lineH1, lineH4, lineV7});
        crossroad11.addHowToGo(lineV8, new Line[] {lineH1, lineH4, lineV2});

        crossroad12.setEntrances(new Line[] {lineH4, lineH5, lineV10});
        crossroad12.setExits(new Line[] {lineH3, lineH6, lineV9});
        crossroad12.addHowToGo(lineH4, new Line[] {lineH6, lineV9});
        crossroad12.addHowToGo(lineH5, new Line[] {lineH3, lineV9});
        crossroad12.addHowToGo(lineV10, new Line[] {lineH3, lineH6});


        crossroad13.setEntrances(new Line[] {lineH6, lineH7, lineV3});
        crossroad13.setExits(new Line[] {lineH5, lineH8, lineV4});
        crossroad13.addHowToGo(lineH6, new Line[] {lineH8, lineV4});
        crossroad13.addHowToGo(lineH7, new Line[] {lineH5, lineV4});
        crossroad13.addHowToGo(lineV3, new Line[] {lineH5, lineH8});

        crossroad14.setEntrances(new Line[] {lineH8, lineH9, lineV5});
        crossroad14.setExits(new Line[] {lineH7, lineH10, lineV6});
        crossroad14.addHowToGo(lineH8, new Line[] {lineH10, lineV6});
        crossroad14.addHowToGo(lineH9, new Line[] {lineH7, lineV6});
        crossroad14.addHowToGo(lineV5, new Line[] {lineH7, lineH10});

        crossroad15.setEntrances(new Line[] {lineH10, lineH11, lineV12});
        crossroad15.setExits(new Line[] {lineH9, lineH12, lineV11});
        crossroad15.addHowToGo(lineH10,new Line[] {lineH12, lineV11});
        crossroad15.addHowToGo(lineH11,new Line[] {lineH9, lineV11});
        crossroad15.addHowToGo(lineV12,new Line[] {lineH9, lineH12});

        crossroad21.setEntrances(new Line[] {lineH13, lineV7, lineV14});
        crossroad21.setExits(new Line[] {lineH14, lineV8, lineV13});
        crossroad21.addHowToGo(lineH13, new Line[] {lineV8, lineV13});
        crossroad21.addHowToGo(lineV7, new Line[] {lineH14, lineV13});
        crossroad21.addHowToGo(lineV14, new Line[] {lineH14, lineV8});

        crossroad22.setEntrances(new Line[] {lineH14, lineH15, lineV9});
        crossroad22.setExits(new Line[] {lineH13, lineH16, lineV10});
        crossroad22.addHowToGo(lineH14, new Line[] {lineH16, lineV10});
        crossroad22.addHowToGo(lineH15, new Line[] {lineH13, lineV10});
        crossroad22.addHowToGo(lineV9, new Line[] {lineH13, lineH16});


        crossroad23.setEntrances(new Line[] {lineH16, lineH17, lineV17, lineV18});
        crossroad23.setExits(new Line[] {lineH15, lineH18, lineV15, lineV16});
        crossroad23.addHowToGo(lineH16, new Line[] {lineH18, lineV15});
        crossroad23.addHowToGo(lineH17, new Line[] {lineH15, lineV16});
        crossroad23.addHowToGo(lineV17, new Line[] {lineH15, lineH18});
        crossroad23.addHowToGo(lineV18, new Line[] {lineH15, lineH18});

        crossroad24.setEntrances(new Line[] {lineH18, lineV20, lineES2});
        crossroad24.setExits(new Line[] {lineH17, lineV19, lineES1});
        crossroad24.addHowToGo(lineH18, new Line[] {lineV19, lineES2});
        crossroad24.addHowToGo(lineV20, new Line[] {lineH17, lineES2});
        crossroad24.addHowToGo(lineES1, new Line[] {lineH17, lineV19});

        crossroad31.setEntrances(new Line[] {lineH20, lineH21, lineV13, lineV22});
        crossroad31.setExits(new Line[] {lineH19, lineH22, lineV14, lineV21});
        crossroad31.addHowToGo(lineH20, new Line[] {lineH22, lineV14, lineV21});
        crossroad31.addHowToGo(lineH21, new Line[] {lineH19, lineV14, lineV21});
        crossroad31.addHowToGo(lineV13, new Line[] {lineH19, lineH22, lineV21});
        crossroad31.addHowToGo(lineV22, new Line[] {lineH19, lineH22, lineV14});

        crossroad32.setEntrances(new Line[] {lineH22, lineH23, lineH24,lineV24});
        crossroad32.setExits(new Line[] {lineH21, lineH25, lineH26, lineV23});
        crossroad32.addHowToGo(lineH22, new Line[] {lineH25, lineV23});
        crossroad32.addHowToGo(lineH23, new Line[] {lineH21});
        crossroad32.addHowToGo(lineH24, new Line[] {lineV23});
        crossroad32.addHowToGo(lineV24, new Line[] {lineH21, lineH26});

        crossroad33.setEntrances(new Line[] {lineH25, lineH26, lineH27, lineH28, lineV15, lineV16, lineV27, lineV28});
        crossroad33.setExits(new Line[] {lineH23, lineH24, lineH29, lineH30, lineV17, lineV18, lineV25, lineV26});
        crossroad33.addHowToGo(lineH25, new Line[] {lineV17, lineH23});
        crossroad33.addHowToGo(lineH26, new Line[] {lineH30, lineV25});
        crossroad33.addHowToGo(lineH27, new Line[] {lineH23, lineV18});
        crossroad33.addHowToGo(lineH28, new Line[] {lineV26});
        crossroad33.addHowToGo(lineV15, new Line[] {lineH23, lineV25});
        crossroad33.addHowToGo(lineV16, new Line[] {lineH29});
        crossroad33.addHowToGo(lineV27, new Line[] {lineH24});
        crossroad33.addHowToGo(lineV28, new Line[] {lineH30, lineV18});


        crossroad34.setEntrances(new Line[] {lineH29, lineH30, lineH31, lineH32, lineV19});
        crossroad34.setExits(new Line[] {lineH27, lineH28, lineH33, lineH34, lineV20});
        crossroad34.addHowToGo(lineH29, new Line[] {lineV20});
        crossroad34.addHowToGo(lineH30, new Line[] {lineH34});
        crossroad34.addHowToGo(lineH31, new Line[] {lineH27, lineV20});
        crossroad34.addHowToGo(lineH32, new Line[] {lineH28});
        crossroad34.addHowToGo(lineV19, new Line[] {lineH27, lineH33});

        crossroad35.setEntrances(new Line[] {lineH33, lineH34, lineH35, lineH36, lineV11, lineV30});
        crossroad35.setExits(new Line[] {lineH31, lineH32, lineH37, lineH38, lineV12, lineV29});
        crossroad35.addHowToGo(lineH33, new Line[] {lineV12});
        crossroad35.addHowToGo(lineH34, new Line[] {lineH38, lineV29});
        crossroad35.addHowToGo(lineH35, new Line[] {lineH31, lineV12});
        crossroad35.addHowToGo(lineH36, new Line[] {lineV29});
        crossroad35.addHowToGo(lineV11, new Line[] {lineH31, lineH37, lineV29});
        crossroad35.addHowToGo(lineV30, new Line[] {lineH32, lineH38, lineV12});

    }

    private void initializeCrossroads(){
        List<Road> roads11 = new ArrayList<>();
        roads11.add(roadV1);
        roads11.add(roadV4);
        roads11.add(roadH1);
        roads11.add(roadH2);
        crossroad11 = new Crossroad(roads11, null, new Point(210, 210),10);

        List<Road> roads12 = new ArrayList<>();
        roads12.add(roadH2);
        roads12.add(roadH3);
        roads12.add(roadV5);
        crossroad12 = new Crossroad(roads12, null, new Point(430, 210),10);

        List<Road> roads13 = new ArrayList<>();
        roads13.add(roadV2);
        roads13.add(roadH3);
        roads13.add(roadH4);
        crossroad13 = new Crossroad(roads13, null, new Point(650, 210), 10);

        List<Road> roads14 = new ArrayList<>();
        roads14.add(roadV3);
        roads14.add(roadH4);
        roads14.add(roadH5);
        crossroad14 = new Crossroad(roads14, null, new Point(870, 210), 10);

        List<Road> roads15 = new ArrayList<>();
        roads15.add(roadV6);
        roads15.add(roadH5);
        roads15.add(roadH6);
        crossroad15 = new Crossroad(roads15, null, new Point(1090, 210), 10);

        List<Road> roads21 = new ArrayList<>();
        roads21.add(roadV4);
        roads21.add(roadV7);
        roads21.add(roadH7);
        crossroad21 = new Crossroad(roads21, null, new Point(210, 430), 10);

        List<Road> roads22 = new ArrayList<>();
        roads22.add(roadV5);
        roads22.add(roadH7);
        roads22.add(roadH8);
        crossroad22 = new Crossroad(roads22, null, new Point(430, 430), 10);

        List<Road> roads23 = new ArrayList<>();
        roads23.add(roadV8);
        roads23.add(roadV9);
        roads23.add(roadH8);
        crossroad23 = new Crossroad(roads23, null, new Point(650, 430), 22);

        List<Road> roads24 = new ArrayList<>();
        roads24.add(roadV10);
        roads24.add(roadH9);
        roads24.add(roadES);
        crossroad24 = new Crossroad(roads24, null, new Point(870, 430), 10);

        List<Road> roads31 = new ArrayList<>();
        roads31.add(roadV7);
        roads31.add(roadV11);
        roads31.add(roadH10);
        roads31.add(roadH11);
        crossroad31 = new Crossroad(roads31, null, new Point(210, 650), 10);

        List<Road> roads32 = new ArrayList<>();
        roads32.add(roadV12);
        roads32.add(roadH11);
        roads32.add(roadH13);
        crossroad32 = new Crossroad(roads32, null, new Point(430, 650), 22);

        List<Road> roads33 = new ArrayList<>();
        roads33.add(roadV9);
        roads33.add(roadV13);
        roads33.add(roadH12);
        roads33.add(roadH15);
        crossroad33 = new Crossroad(roads33, null, new Point(650, 650), 22);

        List<Road> roads34 = new ArrayList<>();
        roads34.add(roadV10);
        roads34.add(roadH14);
        roads34.add(roadH17);
        crossroad34 = new Crossroad(roads34, null, new Point(870, 650), 22);

        List<Road> roads35 = new ArrayList<>();
        roads35.add(roadV6);
        roads35.add(roadV15);
        roads35.add(roadH16);
        roads35.add(roadH19);
        crossroad35 = new Crossroad(roads35, null, new Point(1090, 650), 22);
    }

    private void initializeRoads(){
        List<PedestrianCrossing> pedestrianCrossingsV1 = new ArrayList<>();
        pedestrianCrossingsV1.add(pedestrianCrossingH1);
        List<Line> linesV1 = new ArrayList<>();
        linesV1.add(lineV1);
        linesV1.add(lineV2);
        roadV1 = new Road(50, pedestrianCrossingsV1, linesV1,
                new Point(210,0), new Point(210,200), "2way", false, new Point(210,0))
                .setName("roadV1");

        List<PedestrianCrossing> pedestrianCrossingsV2 = new ArrayList<>();
        pedestrianCrossingsV2.add(pedestrianCrossingH2);
        List<Line> linesV2 = new ArrayList<>();
        linesV2.add(lineV3);
        linesV2.add(lineV4);
        roadV2 = new Road(50,pedestrianCrossingsV2, linesV2,
                new Point(650,0),new Point(650, 200),"2way", false, new Point(650,0))
                .setName("roadV2");

        List<PedestrianCrossing> pedestrianCrossingsV3 = new ArrayList<>();
        pedestrianCrossingsV3.add(pedestrianCrossingH3);
        List<Line> linesV3 = new ArrayList<>();
        linesV3.add(lineV5);
        linesV3.add(lineV6);
        roadV3 = new Road(50, pedestrianCrossingsV3, linesV3,
                new Point(870,0), new Point(870,200), "2way", false, new Point(870,0))
                .setName("roadV3");

        List<PedestrianCrossing> pedestrianCrossingsV4 = new ArrayList<>();
        pedestrianCrossingsV4.add(pedestrianCrossingH4);
        pedestrianCrossingsV4.add(pedestrianCrossingH7);
        List<Line> linesV4 = new ArrayList<>();
        linesV4.add(lineV7);
        linesV4.add(lineV8);
        roadV4 = new Road(50, pedestrianCrossingsV4, linesV4,
                new Point(210,220), new Point(210,420), "2way", false, null)
                .setName("roadV4");

        List<PedestrianCrossing> pedestrianCrossingsV5 = new ArrayList<>();
        pedestrianCrossingsV5.add(pedestrianCrossingH5);
        pedestrianCrossingsV5.add(pedestrianCrossingH8);
        List<Line> linesV5 = new ArrayList<>();
        linesV5.add(lineV9);
        linesV5.add(lineV10);
        roadV5 = new Road(50, pedestrianCrossingsV5, linesV5,
                new Point(430,220), new Point(430,420), "2way", false, new Point(440,320))
                .setName("roadV5");

        List<PedestrianCrossing> pedestrianCrossingsV6 = new ArrayList<>();
        pedestrianCrossingsV6.add(pedestrianCrossingH6);
        pedestrianCrossingsV6.add(pedestrianCrossingH9);
        pedestrianCrossingsV6.add(pedestrianCrossingH16);
        List<Line> linesV6 = new ArrayList<>();
        linesV6.add(lineV11);
        linesV6.add(lineV12);
        roadV6 = new Road(50, pedestrianCrossingsV6, linesV6,
                new Point(1090,220), new Point(1090,628), "2way", false, new Point(1080,430))
                .setName("roadV6");

        List<PedestrianCrossing> pedestrianCrossingsV7 = new ArrayList<>();
        pedestrianCrossingsV7.add(pedestrianCrossingH10);
        pedestrianCrossingsV7.add(pedestrianCrossingH13);
        List<Line> linesV7 = new ArrayList<>();
        linesV7.add(lineV13);
        linesV7.add(lineV14);
        roadV7 = new Road(50, pedestrianCrossingsV7, linesV7,
                new Point(210,440), new Point(210,640), "2way", false, null)
                .setName("roadV7");

        List<PedestrianCrossing> pedestrianCrossingsV8 = new ArrayList<>();
        pedestrianCrossingsV8.add(pedestrianCrossingH11);
        pedestrianCrossingsV8.add(pedestrianCrossingH14);
        List<Line> linesV8 = new ArrayList<>();
        linesV8.add(lineV15);
        linesV8.add(lineV16);
        roadV8 = new Road(50, pedestrianCrossingsV8, linesV8,
                new Point(638,452), new Point(638,628), "1way", false, null)
                .setName("roadV8");

        List<PedestrianCrossing> pedestrianCrossingsV9 = new ArrayList<>();
        pedestrianCrossingsV9.add(pedestrianCrossingH11);
        pedestrianCrossingsV9.add(pedestrianCrossingH14);
        List<Line> linesV9 = new ArrayList<>();
        linesV9.add(lineV17);
        linesV9.add(lineV18);
        roadV9 = new Road(50, pedestrianCrossingsV9, linesV9,
                new Point(662,452), new Point(662,628), "1way", false, null)
                .setName("roadV9");

        List<PedestrianCrossing> pedestrianCrossingsV10 = new ArrayList<>();
        pedestrianCrossingsV10.add(pedestrianCrossingH12);
        pedestrianCrossingsV10.add(pedestrianCrossingH15);
        List<Line> linesV10 = new ArrayList<>();
        linesV10.add(lineV19);
        linesV10.add(lineV20);
        roadV10 = new Road(50, pedestrianCrossingsV10, linesV10,
                new Point(870,440), new Point(870,628), "2way", false, null)
                .setName("roadV10");

        List<PedestrianCrossing> pedestrianCrossingsV11 = new ArrayList<>();
        pedestrianCrossingsV11.add(pedestrianCrossingH17);
        List<Line> linesV11 = new ArrayList<>();
        linesV11.add(lineV21);
        linesV11.add(lineV22);
        roadV11 = new Road(50, pedestrianCrossingsV11, linesV11,
                new Point(210,660), new Point(210,800), "2way", false, new Point(210,800))
                .setName("roadV11");

        List<PedestrianCrossing> pedestrianCrossingsV12 = new ArrayList<>();
        pedestrianCrossingsV12.add(pedestrianCrossingH18);
        List<Line> linesV12 = new ArrayList<>();
        linesV12.add(lineV23);
        linesV12.add(lineV24);
        roadV12 = new Road(50, pedestrianCrossingsV12, linesV12,
                new Point(430,672), new Point(430,800), "2way", false, new Point(430,800))
                .setName("roadV12");

        List<PedestrianCrossing> pedestrianCrossingsV13 = new ArrayList<>();
        pedestrianCrossingsV13.add(pedestrianCrossingH19);
        List<Line> linesV13 = new ArrayList<>();
        linesV13.add(lineV25);
        linesV13.add(lineV26);
        roadV13 = new Road(50, pedestrianCrossingsV13, linesV13,
                new Point(638,672), new Point(638,800), "1way", false, new Point(638,800))
                .setName("roadV13");

        List<PedestrianCrossing> pedestrianCrossingsV14 = new ArrayList<>();
        pedestrianCrossingsV14.add(pedestrianCrossingH19);
        List<Line> linesV14 = new ArrayList<>();
        linesV14.add(lineV27);
        linesV14.add(lineV28);
        roadV14 = new Road(50, pedestrianCrossingsV14, linesV14,
                new Point(662,672), new Point(662,800), "1way", false, new Point(662,800))
                .setName("roadV14");

        List<PedestrianCrossing> pedestrianCrossingsV15 = new ArrayList<>();
        pedestrianCrossingsV15.add(pedestrianCrossingH20);
        List<Line> linesV15 = new ArrayList<>();
        linesV15.add(lineV29);
        linesV15.add(lineV30);
        roadV15 = new Road(50, pedestrianCrossingsV15, linesV15,
                new Point(1090,672), new Point(1090,800), "2way", false, new Point(1090,800))
                .setName("roadV15");

//Horizontal

        List<PedestrianCrossing> pedestrianCrossingsH1 = new ArrayList<>();
        pedestrianCrossingsH1.add(pedestrianCrossingV1);
        List<Line> linesH1 = new ArrayList<>();
        linesH1.add(lineH1);
        linesH1.add(lineH2);
        roadH1 = new Road(50, pedestrianCrossingsH1, linesH1,
                new Point(0,210), new Point(200,210), "2way", false, new Point(0,210))
                .setName("roadH1");

        List<PedestrianCrossing> pedestrianCrossingsH2 = new ArrayList<>();
        pedestrianCrossingsH2.add(pedestrianCrossingV2);
        pedestrianCrossingsH2.add(pedestrianCrossingV3);
        List<Line> linesH2 = new ArrayList<>();
        linesH2.add(lineH3);
        linesH2.add(lineH4);
        roadH2 = new Road(50, pedestrianCrossingsH2, linesH2,
                new Point(220,210), new Point(420,210), "2way", false, null)
                .setName("roadH2");

        List<PedestrianCrossing> pedestrianCrossingsH3 = new ArrayList<>();
        pedestrianCrossingsH3.add(pedestrianCrossingV4);
        pedestrianCrossingsH3.add(pedestrianCrossingV5);
        List<Line> linesH3 = new ArrayList<>();
        linesH3.add(lineH5);
        linesH3.add(lineH6);
        roadH3 = new Road(50, pedestrianCrossingsH3, linesH3,
                new Point(440,210), new Point(640,210), "2way", false, null)
                .setName("roadH3");

        List<PedestrianCrossing> pedestrianCrossingsH4 = new ArrayList<>();
        pedestrianCrossingsH4.add(pedestrianCrossingV6);
        pedestrianCrossingsH4.add(pedestrianCrossingV7);
        List<Line> linesH4 = new ArrayList<>();
        linesH4.add(lineH7);
        linesH4.add(lineH8);
        roadH4 = new Road(50, pedestrianCrossingsH4, linesH4,
                new Point(660,210), new Point(860,210), "2way", false, null)
                .setName("roadH4");

        List<PedestrianCrossing> pedestrianCrossingsH5 = new ArrayList<>();
        pedestrianCrossingsH5.add(pedestrianCrossingV8);
        pedestrianCrossingsH5.add(pedestrianCrossingV9);
        List<Line> linesH5 = new ArrayList<>();
        linesH5.add(lineH9);
        linesH5.add(lineH10);
        roadH5 = new Road(50, pedestrianCrossingsH5, linesH5,
                new Point(880,210), new Point(1080,210), "2way", false, null)
                .setName("roadH5");

        List<PedestrianCrossing> pedestrianCrossingsH6 = new ArrayList<>();
        pedestrianCrossingsH6.add(pedestrianCrossingV10);
        List<Line> linesH6 = new ArrayList<>();
        linesH6.add(lineH11);
        linesH6.add(lineH12);
        roadH6 = new Road(50, pedestrianCrossingsH6, linesH6,
                new Point(1100,210), new Point(1300,210), "2way", false, new Point(1300,210))
                .setName("roadH6");

        List<PedestrianCrossing> pedestrianCrossingsH7 = new ArrayList<>();
        pedestrianCrossingsH7.add(pedestrianCrossingV11);
        pedestrianCrossingsH7.add(pedestrianCrossingV12);
        List<Line> linesH7 = new ArrayList<>();
        linesH7.add(lineH13);
        linesH7.add(lineH14);
        roadH7 = new Road(50, pedestrianCrossingsH7, linesH7,
                new Point(220,430), new Point(420,430), "2way", false, null)
                .setName("roadH7");

        List<PedestrianCrossing> pedestrianCrossingsH8 = new ArrayList<>();
        pedestrianCrossingsH8.add(pedestrianCrossingV13);
        pedestrianCrossingsH8.add(pedestrianCrossingV14);
        List<Line> linesH8 = new ArrayList<>();
        linesH8.add(lineH15);
        linesH8.add(lineH16);
        roadH8 = new Road(50, pedestrianCrossingsH8, linesH8,
                new Point(440,430), new Point(638,430), "2way", false, null)
                .setName("roadH8");

        List<PedestrianCrossing> pedestrianCrossingsH9 = new ArrayList<>();
        pedestrianCrossingsH9.add(pedestrianCrossingV15);
        pedestrianCrossingsH9.add(pedestrianCrossingV16);
        List<Line> linesH9 = new ArrayList<>();
        linesH9.add(lineH17);
        linesH9.add(lineH18);
        roadH9 = new Road(50, pedestrianCrossingsH9, linesH9,
                new Point(672,430), new Point(860,430), "2way", false, null)
                .setName("roadH9");

        List<PedestrianCrossing> pedestrianCrossingsH10 = new ArrayList<>();
        pedestrianCrossingsH10.add(pedestrianCrossingV17);
        List<Line> linesH10 = new ArrayList<>();
        linesH10.add(lineH19);
        linesH10.add(lineH20);
        roadH10 = new Road(50, pedestrianCrossingsH10, linesH10,
                new Point(0,650), new Point(200,650), "2way", false, new Point(0,650))
                .setName("roadH10");

        List<PedestrianCrossing> pedestrianCrossingsH11 = new ArrayList<>();
        pedestrianCrossingsH11.add(pedestrianCrossingV18);
        pedestrianCrossingsH11.add(pedestrianCrossingV19);
        List<Line> linesH11 = new ArrayList<>();
        linesH11.add(lineH21);
        linesH11.add(lineH22);
        roadH11 = new Road(50, pedestrianCrossingsH11, linesH11,
                new Point(220,650), new Point(408,650), "2way", false, null)
                .setName("roadH11");

        List<PedestrianCrossing> pedestrianCrossingsH12 = new ArrayList<>();
        pedestrianCrossingsH12.add(pedestrianCrossingV20);
        pedestrianCrossingsH12.add(pedestrianCrossingV21);
        List<Line> linesH12 = new ArrayList<>();
        linesH12.add(lineH23);
        linesH12.add(lineH24);
        roadH12 = new Road(70, pedestrianCrossingsH12, linesH12,
                new Point(452,638), new Point(628,638), "1way", false, new Point(540,628))
                .setName("roadH12");

        List<PedestrianCrossing> pedestrianCrossingsH13 = new ArrayList<>();
        pedestrianCrossingsH13.add(pedestrianCrossingV20);
        pedestrianCrossingsH13.add(pedestrianCrossingV21);
        List<Line> linesH13 = new ArrayList<>();
        linesH13.add(lineH25);
        linesH13.add(lineH26);
        roadH13 = new Road(70, pedestrianCrossingsH13, linesH13,
                new Point(452,662), new Point(628,662), "1way", false, null)
                .setName("roadH13");

        List<PedestrianCrossing> pedestrianCrossingsH14 = new ArrayList<>();
        pedestrianCrossingsH14.add(pedestrianCrossingV22);
        pedestrianCrossingsH14.add(pedestrianCrossingV23);
        List<Line> linesH14 = new ArrayList<>();
        linesH14.add(lineH27);
        linesH14.add(lineH28);
        roadH14 = new Road(70, pedestrianCrossingsH14, linesH14,
                new Point(672,638), new Point(848,638), "1way", false, null)
                .setName("roadH14");

        List<PedestrianCrossing> pedestrianCrossingsH15 = new ArrayList<>();
        pedestrianCrossingsH15.add(pedestrianCrossingV22);
        pedestrianCrossingsH15.add(pedestrianCrossingV23);
        List<Line> linesH15 = new ArrayList<>();
        linesH15.add(lineH29);
        linesH15.add(lineH30);
        roadH15 = new Road(70, pedestrianCrossingsH15, linesH15,
                new Point(672,662), new Point(848,662), "1way", false, null)
                .setName("roadH15");

        List<PedestrianCrossing> pedestrianCrossingsH16 = new ArrayList<>();
        pedestrianCrossingsH16.add(pedestrianCrossingV24);
        pedestrianCrossingsH16.add(pedestrianCrossingV25);
        List<Line> linesH16 = new ArrayList<>();
        linesH16.add(lineH31);
        linesH16.add(lineH32);
        roadH16 = new Road(70, pedestrianCrossingsH16, linesH16,
                new Point(892,638), new Point(1068,638), "1way", false, null)
                .setName("roadH16");

        List<PedestrianCrossing> pedestrianCrossingsH17 = new ArrayList<>();
        pedestrianCrossingsH17.add(pedestrianCrossingV24);
        pedestrianCrossingsH17.add(pedestrianCrossingV25);
        List<Line> linesH17 = new ArrayList<>();
        linesH17.add(lineH33);
        linesH17.add(lineH34);
        roadH17 = new Road(70, pedestrianCrossingsH17, linesH17,
                new Point(892,662), new Point(1068,662), "1way", false, null)
                .setName("roadH17");

        List<PedestrianCrossing> pedestrianCrossingsH18 = new ArrayList<>();
        pedestrianCrossingsH18.add(pedestrianCrossingV26);
        List<Line> linesH18 = new ArrayList<>();
        linesH18.add(lineH35);
        linesH18.add(lineH36);
        roadH18 = new Road(70, pedestrianCrossingsH18, linesH18,
                new Point(1112,638), new Point(1300,638), "1way", false, new Point(1300,638))
                .setName("roadH18");

        List<PedestrianCrossing> pedestrianCrossingsH19 = new ArrayList<>();
        pedestrianCrossingsH19.add(pedestrianCrossingV26);
        List<Line> linesH19 = new ArrayList<>();
        linesH19.add(lineH37);
        linesH19.add(lineH38);
        roadH19 = new Road(70, pedestrianCrossingsH19, linesH19,
                new Point(1112,662), new Point(1300,662), "1way", false, new Point(1300,662))
                .setName("roadH19");

        List<Line> linesES = new ArrayList<>();
        linesES.add(lineES1);
        linesES.add(lineES2);
        roadES = new Road(20, null, linesES,
                new Point(880,430), new Point(885,430), "2way", false, new Point(885,430))
                .setName("roadES");
    }

    private void initializePedestrianCrossings(){
        pedestrianCrossingH1 = new PedestrianCrossing(new Point(210,187), streetLightsP2wayV, 20);
        pedestrianCrossingH2 = new PedestrianCrossing(new Point(650,187), null,20);
        pedestrianCrossingH3 = new PedestrianCrossing(new Point(870,187), null,20);
        pedestrianCrossingH4 = new PedestrianCrossing(new Point(210,233), streetLightsP2wayV,20);
        pedestrianCrossingH5 = new PedestrianCrossing(new Point(430,233), streetLightsP2wayV,20);
        pedestrianCrossingH6 = new PedestrianCrossing(new Point(1090,233), streetLightsP2wayV,20);
        pedestrianCrossingH7 = new PedestrianCrossing(new Point(210,407), streetLightsP2wayV,20);
        pedestrianCrossingH8 = new PedestrianCrossing(new Point(430,407), null,20);
        pedestrianCrossingH9 = new PedestrianCrossing(new Point(1090,407), null,20);
        pedestrianCrossingH10 = new PedestrianCrossing(new Point(210,453), streetLightsP2wayV,20);
        pedestrianCrossingH11 = new PedestrianCrossing(new Point(650,466), streetLightsP1wayVEW,44);
        pedestrianCrossingH12 = new PedestrianCrossing(new Point(870,453), null,20);
        pedestrianCrossingH13 = new PedestrianCrossing(new Point(210,627), streetLightsP2wayV,20);
        pedestrianCrossingH14 = new PedestrianCrossing(new Point(650,615), streetLightsP1wayVN,44);
        pedestrianCrossingH15 = new PedestrianCrossing(new Point(870,615), streetLightsP2wayVN2,20);
        pedestrianCrossingH16 = new PedestrianCrossing(new Point(1090,615), streetLightsP2wayVN2,20);
        pedestrianCrossingH17 = new PedestrianCrossing(new Point(210,673), streetLightsP2wayV,20);
        pedestrianCrossingH18 = new PedestrianCrossing(new Point(430,685), streetLightsP2wayVTo1way,20);
        pedestrianCrossingH19 = new PedestrianCrossing(new Point(650,685), streetLightsP1wayVS,44);
        pedestrianCrossingH20 = new PedestrianCrossing(new Point(1090,685), streetLightsP2wayVS2,20);

        pedestrianCrossingV1 = new PedestrianCrossing(new Point(187,210), streetLightsP2wayH,20);
        pedestrianCrossingV2 = new PedestrianCrossing(new Point(233,210), streetLightsP2wayH,20);
        pedestrianCrossingV3 = new PedestrianCrossing(new Point(407,210), streetLightsP2wayH,20);
        pedestrianCrossingV4 = new PedestrianCrossing(new Point(453,210), streetLightsP2wayH,20);
        pedestrianCrossingV5 = new PedestrianCrossing(new Point(627,210), null,20);
        pedestrianCrossingV6 = new PedestrianCrossing(new Point(673,210), null,20);
        pedestrianCrossingV7 = new PedestrianCrossing(new Point(847,210), null,20);
        pedestrianCrossingV8 = new PedestrianCrossing(new Point(893,210), null,20);
        pedestrianCrossingV9 = new PedestrianCrossing(new Point(1067,210), streetLightsP2wayH,20);
        pedestrianCrossingV10 = new PedestrianCrossing(new Point(1113,210), streetLightsP2wayH,20);
        pedestrianCrossingV11 = new PedestrianCrossing(new Point(233,430), streetLightsP2wayH,20);
        pedestrianCrossingV12 = new PedestrianCrossing(new Point(407,430), null,20);
        pedestrianCrossingV13 = new PedestrianCrossing(new Point(453,430), null,20);
        pedestrianCrossingV14 = new PedestrianCrossing(new Point(615,430), streetLightsP2wayH,20);
        pedestrianCrossingV15 = new PedestrianCrossing(new Point(685,430), streetLightsP2wayH,20);
        pedestrianCrossingV16 = new PedestrianCrossing(new Point(847,430), null,20);
        pedestrianCrossingV17 = new PedestrianCrossing(new Point(187,650), streetLightsP2wayH,20);
        pedestrianCrossingV18 = new PedestrianCrossing(new Point(233,650), streetLightsP2wayH,20);
        pedestrianCrossingV19 = new PedestrianCrossing(new Point(395,650), streetLightsP2wayHTo1way,20);
        pedestrianCrossingV20 = new PedestrianCrossing(new Point(465,650), streetLightsP1wayHTo2way,44);
        pedestrianCrossingV21 = new PedestrianCrossing(new Point(615,650), streetLightsP1wayHW,44);
        pedestrianCrossingV22 = new PedestrianCrossing(new Point(685,650), streetLightsP1wayHE,44);
        pedestrianCrossingV23 = new PedestrianCrossing(new Point(835,650), streetLightsP1wayH2,44);
        pedestrianCrossingV24 = new PedestrianCrossing(new Point(905,650), streetLightsP1wayH2,44);
        pedestrianCrossingV25 = new PedestrianCrossing(new Point(1055,650), streetLightsP1wayH2,44);
        pedestrianCrossingV26 = new PedestrianCrossing(new Point(1125,650), streetLightsP1wayH2,44);
    }

    private void initializeLines(){
        lineV1 = new Line(streetLights2wayV,null, new Point(205,0), new Point(205,200), "S", false);
        lineV2 = new Line(null, new Point(215,0), new Point(215,200), "N", false);
        lineV3 = new Line(null, new Point(645,0), new Point(645,200), "S", false);
        lineV4 = new Line(null, new Point(655,0), new Point(655,200), "N", false);
        lineV5 = new Line(null, new Point(865,0), new Point(865,200), "S", false);
        lineV6 = new Line(null, new Point(875,0), new Point(875,200), "N", false);
        lineV7 = new Line(streetLights2wayV,null, new Point(205,220), new Point(205,420), "S", false);
        lineV8 = new Line(streetLights2wayV,null, new Point(215,220), new Point(215,420), "N", false);
        lineV9 = new Line(null, new Point(425,220), new Point(425,420), "S", false);
        lineV10 = new Line(streetLights2wayV,null, new Point(435,220), new Point(435,420), "N", false);
        lineV11 = new Line(streetLights2wayV2,null, new Point(1085,220), new Point(1085,628), "S", false);
        lineV12 = new Line(streetLights2wayV,null, new Point(1095,220), new Point(1095,628), "N", false);
        lineV13 = new Line(streetLights2wayV,null, new Point(205,440), new Point(205,640), "S", false);
        lineV14 = new Line(streetLights2wayV,null, new Point(215,440), new Point(215,640), "N", false);
        lineV15 = new Line(streetLights1wayVS,null, new Point(633,452), new Point(633,628), "S", false);
        lineV16 = new Line(streetLights1wayVSL,null, new Point(643,452), new Point(643,628), "S", false);
        lineV17 = new Line(streetLights1wayVEW,null, new Point(657,452), new Point(657,628), "N", false);
        lineV18 = new Line(streetLights1wayVEW, null, new Point(667,452), new Point(667,628), "N", false);
        lineV19 = new Line(streetLights2wayV2,null, new Point(865,440), new Point(865,628), "S", false);
        lineV20 = new Line(null, new Point(875,440), new Point(875,628), "N", false);
        lineV21 = new Line(null, new Point(205,660), new Point(205,800), "S", false);
        lineV22 = new Line(streetLights2wayV,null, new Point(215,660), new Point(215,800), "N", false);
        lineV23 = new Line(null, new Point(425,672), new Point(425,800), "S", false);
        lineV24 = new Line(streetLights2wayVTo1way,null, new Point(435,672), new Point(435,800), "N", false);
        lineV25 = new Line(null, new Point(633,672), new Point(633,800), "S", false);
        lineV26 = new Line(null, new Point(643,672), new Point(643,800), "S", false);
        lineV27 = new Line(streetLights1wayVNL, null, new Point(657,672), new Point(657,800), "N", false);
        lineV28 = new Line(streetLights1wayVN, null, new Point(667,672), new Point(667,800), "N", false);
        lineV29 = new Line(null, new Point(1085,672), new Point(1085,800), "S", false);
        lineV30 = new Line(streetLights2wayV2,null, new Point(1095,672), new Point(1095,800), "N", false);

        lineH1 = new Line(null, new Point(0, 205), new Point(200, 205), "W", false);
        lineH2 = new Line(streetLights2wayH,null, new Point(0, 215), new Point(200, 215), "E", false);
        lineH3 = new Line(streetLights2wayH,null, new Point(220, 205), new Point(420, 205), "W", false);
        lineH4 = new Line(streetLights2wayH,null, new Point(220, 215), new Point(420, 215), "E", false);
        lineH5 = new Line(streetLights2wayH,null, new Point(440, 205), new Point(640, 205), "W", false);
        lineH6 = new Line(null, new Point(440, 215), new Point(640, 215), "E", false);
        lineH7 = new Line(null, new Point(660, 205), new Point(860, 205), "W", false);
        lineH8 = new Line(null, new Point(660, 215), new Point(860, 215), "E", false);
        lineH9 = new Line(null, new Point(880, 205), new Point(1080, 205), "W", false);
        lineH10 = new Line(streetLights2wayH,null, new Point(880, 215), new Point(1080, 215), "E", false);
        lineH11 = new Line(streetLights2wayH,null, new Point(1100, 205), new Point(1300, 205), "W", false);
        lineH12 = new Line(null, new Point(1100, 215), new Point(1300, 215), "E", false);
        lineH13 = new Line(streetLights2wayH,null, new Point(220, 425), new Point(420, 425), "W", false);
        lineH14 = new Line(null, new Point(220, 435), new Point(420, 435), "E", false);
        lineH15 = new Line(null, new Point(440, 425), new Point(628, 425), "W", false);
        lineH16 = new Line(streetLights2wayH,null, new Point(440, 435), new Point(628, 435), "E", false);
        lineH17 = new Line(streetLights2wayH,null, new Point(672, 425), new Point(860, 425), "W", false);
        lineH18 = new Line(null, new Point(672, 435), new Point(860, 435), "E", false);
        lineH19 = new Line(null, new Point(0, 645), new Point(200, 645), "W", false);
        lineH20 = new Line(streetLights2wayH,null, new Point(0, 655), new Point(200, 655), "E", false);
        lineH21 = new Line(streetLights2wayH,null, new Point(220, 645), new Point(408, 645), "W", false);
        lineH22 = new Line(streetLights2wayHTo1way, null, new Point(220, 655), new Point(408, 655), "E", false);
        lineH23 = new Line(streetLights1wayHTo2wayW, null, new Point(452, 633), new Point(628, 633), "W", false);
        lineH24 = new Line(streetLights1wayHTo2wayS, null, new Point(452, 643), new Point(628, 643), "W", false);
        lineH25 = new Line(streetLights1wayHEL, null, new Point(452, 657), new Point(628, 657), "E", false);
        lineH26 = new Line(streetLights1wayHE, null, new Point(452, 667), new Point(628, 667), "E", false);
        lineH27 = new Line(streetLights1wayHW,null, new Point(672, 633), new Point(848, 633), "W", false);
        lineH28 = new Line(streetLights1wayHWL,null, new Point(672, 643), new Point(848, 643), "W", false);
        lineH29 = new Line(streetLights1wayHEL2,null, new Point(672, 657), new Point(848, 657), "E", false);
        lineH30 = new Line(streetLights1wayHE2, null, new Point(672, 667), new Point(848, 667), "E", false);
        lineH31 = new Line(streetLights1wayHW2, null, new Point(892, 633), new Point(1068, 633), "W", false);
        lineH32 = new Line(streetLights1wayHW2, null, new Point(892, 643), new Point(1068, 643), "W", false);
        lineH33 = new Line(streetLights1wayHEL2,null, new Point(892, 657), new Point(1068, 657), "E", false);
        lineH34 = new Line(streetLights1wayHE2, null, new Point(892, 667), new Point(1068, 667), "E", false);
        lineH35 = new Line(streetLights1wayHW2,null, new Point(1112, 633), new Point(1300, 633), "W", false);
        lineH36 = new Line(streetLights1wayHWL2,null, new Point(1112, 643), new Point(1300, 643), "W", false);
        lineH37 = new Line(null, new Point(1112, 657), new Point(1300, 657), "E", false);
        lineH38 = new Line(null, new Point(1112, 667), new Point(1300, 667), "E", false);

        lineES1 = new Line(null, new Point(880, 425), new Point(885, 425), "W", false);
        lineES2 = new Line(null, new Point(880, 435), new Point(885, 435), "E", false);

    }

    private void initializeStreetLights() throws Exception {
        streetLights2wayV = new StreetLights(lightsLength + 9000,lightsLength + 5000,false, StreetLights.RED, 1000 );
        streetLights2wayH = new StreetLights(lightsLength + 9000,lightsLength + 5000,false, StreetLights.YELLOW, 0);
        streetLights1wayHE = new StreetLights(42000 + (lightsLength -5000) * 3,lightsLength + 11000,false, StreetLights.RED, 29000 + (lightsLength -5000) * 2);
        streetLights1wayHW = new StreetLights(42000 + (lightsLength -5000) * 3,lightsLength + 11000,false, StreetLights.RED, 40000 + (lightsLength -5000) * 3);
        streetLights1wayHEL = new StreetLights(50000 + (lightsLength -5000) * 3,lightsLength + 3000,true, StreetLights.RED, 29000 + (lightsLength -5000) * 2);
        streetLights1wayHWL = new StreetLights(50000 + (lightsLength -5000) * 3,lightsLength + 3000,true, StreetLights.RED, 48000 + (lightsLength -5000) * 3);
        streetLights1wayVN = new StreetLights(42000 + (lightsLength -5000) * 3,lightsLength + 11000,false, StreetLights.RED, 10000 + (lightsLength -5000));
        streetLights1wayVS = new StreetLights(42000 + (lightsLength -5000) * 3,lightsLength + 11000,false, StreetLights.RED_YELLOW, 0);
        streetLights1wayVNL = new StreetLights(50000 + (lightsLength -5000) * 3,lightsLength + 3000,true, StreetLights.RED, 18000 + (lightsLength -5000));
        streetLights1wayVSL = new StreetLights(50000 + (lightsLength -5000) * 3,lightsLength + 3000,true, StreetLights.RED_YELLOW, 0);
        streetLights1wayHTo2wayW = new StreetLights(14000 + (lightsLength -5000),lightsLength + 13000,true, StreetLights.RED, 12000 + (lightsLength -5000));
        streetLights1wayHTo2wayS = new StreetLights(27000 + (lightsLength -5000) * 2,5000,true, StreetLights.RED, 25000 + (lightsLength -5000) * 2);
        streetLights2wayHTo1way = new StreetLights(22000 + (lightsLength -5000), lightsLength + 5000, false, StreetLights.RED, 12000 + (lightsLength -5000));
        streetLights2wayVTo1way = new StreetLights(22000 + (lightsLength -5000),lightsLength + 5000, false, StreetLights.RED_YELLOW,0);
        streetLights1wayVEW = streetLights2wayV;
        streetLights1wayHE2 = new StreetLights(25000 +(int) ((lightsLength -5000) * 1.5), (int) ((lightsLength -5000) * 0.5) + 16000,false, StreetLights.RED, 12000 + (lightsLength -5000));
        streetLights1wayHW2 = new StreetLights(25000 +(int) ((lightsLength -5000) * 1.5),(int) ((lightsLength -5000) * 0.5)  + 16000,false, StreetLights.RED, 23000 +(int) ((lightsLength -5000) * 1.5));
        streetLights1wayHEL2 = new StreetLights(33000 +(int) ((lightsLength -5000) * 1.5),(int) ((lightsLength -5000) * 0.5)  + 8000,true, StreetLights.RED, 12000 + (lightsLength -5000));
        streetLights1wayHWL2 = new StreetLights(33000 +(int) ((lightsLength -5000) * 1.5),(int) ((lightsLength -5000) * 0.5)  + 8000,true, StreetLights.RED, 31000 +(int) ((lightsLength -5000) * 1.5));
        streetLights2wayV2 = new StreetLights(31000 + (lightsLength -5000),lightsLength + 5000,false, StreetLights.RED_YELLOW, 0);

        streetLightsP2wayV = new StreetLights(lightsLength + 11000,lightsLength + 5000,StreetLights.GREEN, 0);
        streetLightsP2wayH = new StreetLights(lightsLength + 11000, lightsLength + 5000, StreetLights.RED, 2000);
        streetLightsP1wayHW = new StreetLights(44000 + (lightsLength -5000) * 3, lightsLength + 11000,StreetLights.RED, 0);
        streetLightsP1wayHE = new StreetLights(44000 + (lightsLength -5000) * 3, lightsLength + 11000,StreetLights.RED,11000 + (lightsLength -5000));
        streetLightsP1wayVS = new StreetLights(44000 + (lightsLength -5000) * 3, lightsLength + 11000, StreetLights.RED, 30000 + (lightsLength -5000) * 2);
        streetLightsP1wayVN = new StreetLights(44000 + (lightsLength -5000) * 3, lightsLength + 11000, StreetLights.RED, 41000 + (lightsLength -5000) * 3);
        streetLightsP1wayHTo2way = new StreetLights(24000 + (lightsLength -5000), lightsLength + 5000, StreetLights.RED, 0);
        streetLightsP2wayHTo1way = streetLightsP1wayHTo2way;
        streetLightsP2wayVTo1way = new StreetLights(24000 + (lightsLength -5000), lightsLength + 5000, StreetLights.RED, 13000 + (lightsLength -5000));
        streetLightsP1wayVEW = streetLightsP2wayV;
        streetLightsP1wayH2 = new StreetLights(33000 + (lightsLength -5000), lightsLength + 5000, StreetLights.RED, 0);
        streetLightsP2wayVS2 = new StreetLights(27000 +(int) ((lightsLength -5000) * 1.5), (int) ((lightsLength -5000) * 0.5) + 16000, StreetLights.RED, 13000 + (lightsLength -5000));
        streetLightsP2wayVN2 = new StreetLights(27000 +(int) ((lightsLength -5000) * 1.5), (int) ((lightsLength -5000) * 0.5) + 16000, StreetLights.RED, 24000 +(int) ((lightsLength -5000) * 1.5));
    }

    private void initializeRoadSigns(){

    }

    static void changeStreetLightsImageView(StreetLights streetLights){
        if(streetLights.isPedestrianLight())
            pedestrianCrossings.stream().filter(pedestrianCrossing -> pedestrianCrossing.getStreetLights() != null)
                .filter(pedestrianCrossing -> pedestrianCrossing.getStreetLights().equals(streetLights)).forEach(PedestrianCrossing::setImageView);
        else
            lines.stream().filter(line -> line.getStreetLights() != null)
                .filter(line -> line.getStreetLights().equals(streetLights)).forEach(Line::setImageView);
    }

    static List<Road> getRoads() {
        return roads;
    }

    static List<StreetLights> getStreetLights() {
        return streetLights;
    }

    static List<Line> getLines() {
        return lines;
    }

    static List<PedestrianCrossing> getPedestrianCrossings() {
        return pedestrianCrossings;
    }

    public static int getLightsLength() {
        return lightsLength;
    }

    public static void setLightsLength(int lightsLength) {
        Initialize.lightsLength = lightsLength;
    }

    public static List<Crossroad> getCrossroads() {
        return crossroads;
    }
}
