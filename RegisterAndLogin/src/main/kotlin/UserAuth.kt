
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import org.mindrot.jbcrypt.BCrypt

class UserAuth{

    private val gson = Gson()
    private val file = File("users_data.json")

    // Load existing users from file
    fun loadUsers(): MutableMap<String, String> {
        if (file.exists() && file.readText().isNotBlank()) {
            return gson.fromJson(file.readText(), MutableMap::class.java) as MutableMap<String, String>
        }
        return mutableMapOf()
    }

    // Save users to file
    private fun saveUsers(usersMaps: MutableMap<String, String>) {

        val gsonPretty = GsonBuilder().setPrettyPrinting().create() // Convert map to JSON
        file.writeText(gsonPretty.toJson(usersMaps)) // Save JSON string to file
    }

    // Register and hashed the input password.
    fun registerUser(usersMaps: MutableMap<String, String>, email: String, password: String) {

        val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
        usersMaps[email] = hashedPassword
        saveUsers(usersMaps)
    }

    // Check if email is valid.
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return email.matches(emailRegex)
    }

}