//package ru.job4j.carParking.parking;
//
//import ru.job4j.carParking.cars.ICar;
//import ru.job4j.carParking.cars.Truck;
//
//import java.util.Arrays;
//
///**
// * Parking
// *
// * @author Vladislav Nechaev
// * @since 19.06.2020
// */
//public class ParkingOld implements IParking {
//
//    private final ICar emptyCar;
//    private int passCounter;
//    private int truckCounter;
//    private int passLimit;
//    private int truckLimit;
//    private ICar[] parking;
////    private List<ICar> parking;
//
//    public ParkingOld(int passCarNumber, int truckNumber) throws IllegalArgumentException {
//        if (passCarNumber < 0 || truckNumber < 0 || (passCarNumber + truckNumber < 1)) {
//            throw new IllegalArgumentException("Wrong cars number.");
//        }
//        this.passLimit = passCarNumber;
//        this.truckLimit = truckNumber;
//        this.parking = new ICar[passCarNumber + truckNumber];
////        this.parking = new ArrayList<>(passCarNumber + truckNumber);
//        this.emptyCar = Truck.getTruck(0);
//        createEmptyParking();
//    }
//
//    @Override
//    public boolean addCar(ICar car) throws IllegalArgumentException, LimitException {
//        boolean result = false;
//        int parkingWeight = car.getParkingWeight();
//        if (parkingWeight <= 0) {
//            throw new IllegalArgumentException("Wrong parking weight. The Car with id="
//                    + car.getID()
//                    + " has parking weight = "
//                    + parkingWeight);
//        }
//        if (contains(car)) {
//            throw new IllegalArgumentException("The parking already has a car with id=" + car.getID());
//        }
//        if (parkingWeight == 1 && passCounter == passLimit) {
//            throw new LimitException("The number of cars has reached the limit.");
//        }
//        int freePlace = findFreeParking(parkingWeight);
//        if (freePlace < 0) {
//            throw new LimitException("The number of cars has reached the limit.");
//        }
//        for (int i = 0; i < parkingWeight; i++) {
//            parking[freePlace++] = car;
////                    freePlace++;
//            result = true;
//        }
//        if (parkingWeight == 1) {
//            passCounter++;
//        } else {
//            truckCounter++;
//        }
//
//        return result;
//    }
//
//
//    @Override
//    public void removeCar(ICar car) {
//
//    }
//
//    private int findFreeParking(int parkingWeight) {
//        int startIndex = -1;
//
//
//        return startIndex;
//    }
//
//    private void createEmptyParking() {
//        for (int i = 0; i < parking.length; i++) {
//            parking[i] = emptyCar;
//        }
//    }
//
//    @Override
//    public boolean contains(ICar car) {
//        return Arrays.asList(parking).contains(car);
//    }
//
////    }
////        }
////            parking.add(emptyCar);
////        for (int i = 0; i < parking.size(); i++) {
////    private void createEmptyParking() {
////
//
////    private boolean isLimitPassCar() {
////               return false;
////    }
//}
