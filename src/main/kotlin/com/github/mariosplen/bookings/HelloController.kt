package bookings

import javafx.fxml.FXML
import javafx.scene.control.Label

class HelloController {
    @FXML
    private lateinit var welcomeText: Label

    fun onHelloButtonClick() {
        welcomeText.text = "Welcome to JavaFX Application!"
    }
}