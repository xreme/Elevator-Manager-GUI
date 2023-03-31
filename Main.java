package gui_manager;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import ElevatorManagementProject.*;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.util.Duration;



public class Main extends Application {
	
	private static int time = 0;
	static MainSceneController sceneController;
	
	public static void elevatorPrint(Elevator e1, Elevator e2) {
			
			System.out.println("TIME: " + time);
			System.out.println("[-- E" +e1.getId()  + "--\t]\t[-- E" +e2.getId()  + "--]");
			System.out.println("|Location: " +  e1.getlocation().currentFloor() + "\t|\t|Location: " +  e2.getlocation().currentFloor());
			System.out.println("|IsMotion: " + e1.getMotion() + "\t|\t|IsMotion: " + e2.getMotion());
			System.out.println("|IsPower: " + e1.isPower() + "\t|\t|IsPower: " + e2.isPower());
			System.out.println("|IsEnabled: " + e1.isEnable() + "|\t|IsEnabled: " + e2.isEnable());
			System.out.println("|Door: " + e1.getDoorStatus()+ "\t|\t|Door: " + e2.getDoorStatus());
			
		}
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//MAKE DECLARATIONS HERE
			
			//Floors
			Floor floor1 = new Floor(1);
			Floor floor2 = new Floor(2);
			Floor floor3 = new Floor(3);
			Floor floor4 = new Floor(4);
			Floor floor5 = new Floor(5);
			Floor floor6 = new Floor(6);
			
			//manager
			ElevatorManagement manager = new ElevatorManagement();
			Door door1 = new Door();
			Door door2 = new Door();
			
			//elevators
			Elevator e1 = new Elevator(door1);
			Elevator e2 = new Elevator(door2);
			
			//special mode handler
			SpecialModeHandler handler = new SpecialModeHandler(manager);
			
			//building system
			BuildingSystem MedicalCampus = new BuildingSystem(handler, floor1);
			InsideButton e1Button = new InsideButton(manager, e1, handler);
			InsideButton e2Button = new InsideButton(manager, e2, handler);
			manager.addElevator(e1);
			manager.addElevator(e2);
			
			//outside buttons
			OutsideButton floor1button = new OutsideButton(manager, floor1); 
			OutsideButton floor2button = new OutsideButton(manager, floor2);
			OutsideButton floor3button = new OutsideButton(manager, floor3);
			OutsideButton floor4button = new OutsideButton(manager, floor4);
			OutsideButton floor5button = new OutsideButton(manager, floor5);
			OutsideButton floor6button = new OutsideButton(manager, floor6);
			
	
			
			//GUI SETUP THINGS
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UI.fxml"));
			Parent root  = loader.load();
			Scene scene = new Scene(root);
			
			//passing parameters over to the GUI
			sceneController = (MainSceneController)loader.getController();
			sceneController.setButtons(e1Button,e2Button);
			sceneController.setHandler(handler);
			sceneController.setFloors(floor1, floor2, floor3, floor4, floor5, floor6);
			sceneController.setBuilding(MedicalCampus);
			sceneController.setOutsideButtons(floor1button, floor2button, floor3button, floor4button, floor5button, floor6button);
			sceneController.setElevators(e1, e2);
			
			//start the GUI
			primaryStage.setScene(scene);
			primaryStage.setTitle("Elevator Manager V3");
			primaryStage.show();
			
			
			//STARTS THE ANIMATION LOOP - PLACE ANTHING YOU WNAT TO BE REPETEADLY CALLED INSIDE THIS SPACE
			Timeline guiLoop = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) ->
	        {
	        	//Print out the elevator
				elevatorPrint(e1,e2);
				sceneController.refreshGUI();
				time +=1;
				
				if (!manager.callEmpty()) {
					manager.takeCall();
				}
				try {
					manager.moveElevators();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	        }));
			guiLoop.setCycleCount(Timeline.INDEFINITE);
			guiLoop.play();
	        
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		
		launch(args);
		
		
		
		
		
	}
	
	
}
