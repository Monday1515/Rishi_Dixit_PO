module Project_OOP {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires jdk.httpserver;
	opens application to javafx.graphics, javafx.fxml;
}
