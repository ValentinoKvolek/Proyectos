import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.callloging.*
import io.ktor.http.HttpStatusCode
import org.mindrot.jbcrypt.BCrypt
import io.ktor.serialization.jackson.jackson
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.CORS
import io.ktor.http.HttpMethod
import io.ktor.http.HttpHeaders

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    val userAuth = UserAuth() //Intances my logic class
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {  }
    }

    install(CORS) {
        anyHost()
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Accept)
    }

    routing {
        post("/login") {
            val parameters = call.receiveParameters()
            val email = parameters["email"]
            val password = parameters["password"]

            if (email != null && password != null) {
                val usersMaps = userAuth.loadUsers() // Load users

                // Check if email exist
                if (usersMaps.containsKey(email)) {
                    val storedHashedPassword = usersMaps[email]!!

                    // Check password
                    if (BCrypt.checkpw(password, storedHashedPassword)) {
                        call.respond(HttpStatusCode.OK, mapOf("status" to "success", "message" to "Inicio de sesión exitoso"))
                        println("Inicio de sesión exitoso")
                    } else {
                        call.respond(HttpStatusCode.Unauthorized, mapOf("status" to "error", "message" to "Contraseña incorrecta"))
                        println("Contraseña incorrecta")
                    }
                } else {
                    call.respond(HttpStatusCode.NotFound, mapOf("status" to "error", "message" to "Email no registrado"))
                    println("Email no registrado")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, mapOf("status" to "error", "message" to "Email o contraseña vacíos"))
            }
        }

        post("/register") {
            val parameters = call.receiveParameters()
            val email = parameters["email"]
            val password = parameters["password"]

            if (email != null && password != null) {
                if (userAuth.isValidEmail(email)) {
                    val usersMaps = userAuth.loadUsers() // load users

                    // Check email
                    if (usersMaps.containsKey(email)) {
                        call.respond(HttpStatusCode.Conflict, mapOf("status" to "error", "message" to "El usuario ya existe"))
                    } else {
                        //Register new user
                        userAuth.registerUser(usersMaps, email, password)
                        call.respond(HttpStatusCode.Created, mapOf("status" to "success", "message" to "Usuario registrado exitosamente"))
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, mapOf("status" to "error", "message" to "Email inválido"))
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, mapOf("status" to "error", "message" to "Email o contraseña vacíos"))
            }
        }
    }
}




