package gui_manager;

import ElevatorManagementProject.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.HashMap;


public class MainSceneController {
	
	
	
	@FXML
	private ImageView imgElevator1;
	@FXML
	private ImageView imgElevator2;
	@FXML
	private Pane panePower;
	@FXML
	private Pane paneFire;
	@FXML
	private Pane paneNoti;
	@FXML
	private Pane paneGen;
	
	Image elevator = new Image(getClass().getResourceAsStream("resoruces/elevator.png"));
	Image elevatorON = new Image(getClass().getResourceAsStream("resoruces/elevatorON.png"));
	Image elevatorNoPower = new Image(getClass().getResourceAsStream("resoruces/elevatorNoPower.png"));
	
	private double y0;
	private double y1;
	Button btnElevator1Int5;
	
	BuildingSystem buildingSys;
	InsideButton e1B;
	InsideButton e2B;
	OutsideButton outBut1;
	OutsideButton outBut2;
	OutsideButton outBut3;
	OutsideButton outBut4;
	OutsideButton outBut5;
	OutsideButton outBut6;
	Elevator e1;
	Elevator e2;
	Floor fl1;
	Floor fl2;
	Floor fl3;
	Floor fl4;
	Floor fl5;
	Floor fl6;
	SpecialModeHandler buildingHandler;
	
	//GUI Setters
	public void setBuilding(BuildingSystem b1) {
		this.buildingSys = b1;
	}
	
	public void setButtons(InsideButton e1, InsideButton e2) {
		this.e1B = e1;
		this.e2B = e2;
	}
	public void setHandler(SpecialModeHandler handler) {
		this.buildingHandler = handler;
	}
	public void setFloors(Floor f1,Floor f2,Floor f3,Floor f4,Floor f5,Floor f6) {
		this.fl1 = f1;
		this.fl2 = f2;
		this.fl3 = f3;
		this.fl4 = f4;
		this.fl5 = f5;
		this.fl6 = f6;
	}
	public void setOutsideButtons(OutsideButton f1, OutsideButton f2, OutsideButton f3, OutsideButton f4, OutsideButton f5, OutsideButton f6){
		this.outBut1 = f1;
		this.outBut2 = f2;
		this.outBut3 = f3;
		this.outBut4 = f4;
		this.outBut5 = f5;
		this.outBut6 = f6;
	}
	public void setElevators(Elevator e1, Elevator e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	//**TESTING** Precision Elevator Movements
	@FXML
	public void moveE1UP(ActionEvent event) {
		System.out.println(imgElevator1.getY());
		imgElevator1.setY(y0-=10);
	}
	@FXML
	public void moveE1UP1() {
		System.out.println(imgElevator1.getY());
		imgElevator1.setY(y0-=10);
	}
	@FXML
	public void moveE1Down(ActionEvent event) {
		System.out.println(imgElevator1.getY());
		imgElevator1.setY(y0+=10);
	}
	@FXML
	public void moveE2UP(ActionEvent event) {
		System.out.println(imgElevator2.getY());
		imgElevator2.setY(y1-=10);
	}
	@FXML
	public void moveE2Down(ActionEvent event) {
		System.out.println(imgElevator2.getY());
		imgElevator2.setY(y1+=10);
	}
	
	//STATUS CHECK
	@FXML
	public void powerStatus() {
		if (buildingSys.getPower()){
			panePower.setStyle("-fx-background-color: lightgreen; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: black");
		}
		else {
			panePower.setStyle("-fx-background-color: salmon; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: black");
		}
		
	}
	@FXML
	public void generatorStatus() {
		if (buildingSys.getGenerator()){
			paneGen.setStyle("-fx-background-color: lightgreen; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: black");
		}
		else {
			paneGen.setStyle("-fx-background-color: salmon; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: black");
		}

	}
	@FXML
	public void fireStatus() {
		if (buildingSys.getFire()){
			paneFire.setStyle("-fx-background-color: salmon; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: black");
		}
		else {
			paneFire.setStyle("-fx-background-color: lightgrey; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: black");
		}
	}
	@FXML
	public void specialMode() {
		if (buildingHandler.isEmpty()){
			paneNoti.setStyle("-fx-background-color: lightgrey; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: black");
		}
		else {
			paneNoti.setStyle("-fx-background-color: salmon; -fx-border-radius: 20; -fx-background-radius: 20; -fx-border-color: black");
		}
		
	}
	

	
	//Elevator Check
	public void updateElevators() {
		
		if (e1.isEnable() && e1.isPower()) {
			imgElevator1.setImage(elevatorON);
		}
		if (e2.isEnable() && e2.isPower()) {
			imgElevator2.setImage(elevatorON);
		}
		if (! e1.isPower()) {
			imgElevator1.setImage(elevatorNoPower);
		}
		if (! e2.isPower()) {
			imgElevator2.setImage(elevatorNoPower);
		}
		if (! e1.isEnable()) {
			imgElevator1.setImage(elevator);
		}
		if (! e2.isEnable()) {
			imgElevator2.setImage(elevator);
		}
		
		
	}

	
	//testing button
	public void disaster(ActionEvent event) {
		buildingSys.setPower(false);
		buildingSys.setGenerator(false);
		buildingSys.setFire(true);
		e1.setPower(false);
		e2.setEnable(false);
		buildingHandler.addEmergencyModes( new MedicalEmergency(fl1, fl1, buildingSys));
	}

		
	//set the location of the elevators
	public void setE1Floor() {
		int Floor = e1.getlocation().currentFloor();
		HashMap<Integer, Integer> floors = new HashMap<>();
		floors.put(1, 0);floors.put(2, -100);floors.put(3, -200);floors.put(4, -300);floors.put(5, -400);floors.put(6, -480);
		imgElevator1.setY(y0=floors.get(Floor));
	}
	public void setE2Floor() {
		int Floor = e2.getlocation().currentFloor();
		HashMap<Integer, Integer> floors = new HashMap<>();
		floors.put(1, 0);floors.put(2, -100);floors.put(3, -200);floors.put(4, -300);floors.put(5, -400);floors.put(6, -480);
		imgElevator2.setY(y1=floors.get(Floor));
	}
	
	
	//ELEVATOR INPUTS
	
	//ELEVATOR 1
	@FXML
	public void E1IntF1(ActionEvent event) throws InterruptedException {
		System.out.println("1.1");
		e1B.callFloor(fl1);
	}
	@FXML
	public void E1IntF2(ActionEvent event) throws InterruptedException {
		System.out.println("1.2");
		e1B.callFloor(fl2);
	}
	@FXML
	public void E1IntF3(ActionEvent event) throws InterruptedException {
		System.out.println("1.3");
		e1B.callFloor(fl3);
	}
	@FXML
	public void E1IntF4(ActionEvent event) throws InterruptedException {
		System.out.println("1.4");
		e1B.callFloor(fl4);
	}
	@FXML
	public void E1IntF5(ActionEvent event) throws InterruptedException {
		System.out.println("1.5");
		e1B.callFloor(fl5);
	}
	@FXML
	public void E1IntF6(ActionEvent event) throws InterruptedException {
		System.out.println("1.6");
		e1B.callFloor(fl6);
	}
	@FXML
	public void E1IntClose(ActionEvent event) throws InterruptedException {
		e1B.closeDoorButton();
	}
	@FXML
	public void E1IntOpen(ActionEvent event) throws InterruptedException {
		e1B.openDoorButton();
	}
	@FXML
	public void E1IntFire(ActionEvent event) throws InterruptedException {
		e1B.setFireAlarm(fl1, buildingSys);
	}
	
	
	//ELEVATOR 2
	@FXML
	public void E2IntF1(ActionEvent event) throws InterruptedException {
		System.out.println("2.1");
		e2B.callFloor(fl1);
	}
	@FXML
	public void E2IntF2(ActionEvent event) throws InterruptedException {
		System.out.println("2.2");
		e2B.callFloor(fl2);
	}
	@FXML
	public void E2IntF3(ActionEvent event) throws InterruptedException {
		System.out.println("2.3");
		e2B.callFloor(fl3);
	}
	@FXML
	public void E2IntF4(ActionEvent event) throws InterruptedException {
		System.out.println("2.4");
		e2B.callFloor(fl4);
	}
	@FXML
	public void E2IntF5(ActionEvent event) throws InterruptedException {
		System.out.println("2.5");
		e2B.callFloor(fl5);
	}
	@FXML
	public void E2IntF6(ActionEvent event) throws InterruptedException {
		//System.out.println("2.6");
			e2B.callFloor(fl6);
	}
	@FXML
	public void E2IntClose(ActionEvent event) throws InterruptedException {
		e2B.closeDoorButton();
	}
	@FXML
	public void E2IntOpen(ActionEvent event) throws InterruptedException {
		e2B.openDoorButton();
	}
	@FXML
	public void E2IntFire(ActionEvent event) throws InterruptedException {
		e2B.setFireAlarm(fl1, buildingSys);
	}
	
	
	//FLOOR OUTISIDE BUTTONS
	
	//FLOOR 1
	@FXML
	public void Fl1ExtUP(ActionEvent event) throws InterruptedException {
		outBut1.callElevator(1);
	}
	//FLOOR 2
	public void Fl2ExtUP(ActionEvent event) throws InterruptedException {
		outBut2.callElevator(1);
	}
	public void Fl2ExtDOWN(ActionEvent event) throws InterruptedException {
		outBut2.callElevator(-1);
	}
	//FLOOR 3
	public void Fl3ExtUP(ActionEvent event) throws InterruptedException {
		outBut3.callElevator(1);
	}
	public void Fl3ExtDOWN(ActionEvent event) throws InterruptedException {
		outBut3.callElevator(-1);
	}
	//FLOOR 4
	public void Fl4ExtUP(ActionEvent event) throws InterruptedException {
		outBut4.callElevator(1);
	}
	public void Fl4ExtDOWN(ActionEvent event) throws InterruptedException {
		outBut4.callElevator(-1);
	}
	//FLOOR 5
	public void Fl5ExtUP(ActionEvent event) throws InterruptedException {
		outBut5.callElevator(1);
	}
	public void Fl5ExtDOWN(ActionEvent event) throws InterruptedException {
		outBut5.callElevator(-1);
	}
	//FLOOR 6
	public void Fl6ExtDOWN(ActionEvent event) throws InterruptedException {
		outBut6.callElevator(-1);
	}
	
	
	//REFRESH THE VARIOUS ELEMENTS OF THE GUI
	
	public void refreshGUI() {
		setE1Floor();
		//moveE1UP1();
		
		setE2Floor();
		powerStatus();
		generatorStatus();
		fireStatus();
		updateElevators();
		specialMode();
		
		}

}
