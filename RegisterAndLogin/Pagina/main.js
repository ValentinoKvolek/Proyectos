// login msj
const loginForm = document.getElementById('loginForm');
if (loginForm) {
    loginForm.addEventListener('submit', async function(event) {
        event.preventDefault(); 

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({
                    email: email,
                    password: password
                })
            });

            const data = await response.json();
            const responseMessage = document.getElementById('responseMessage');

            if (response.ok) {
                responseMessage.innerHTML = `<p style="color: green;">${data.message}</p>`;
            } else {
                responseMessage.innerHTML = `<p style="color: red;">${data.message}</p>`;
            }
        } catch (error) {
            console.error('Error en la solicitud de login:', error);
            document.getElementById('responseMessage').innerHTML = `<p style="color: red;">Error al conectarse al servidor</p>`;
        }
    });
}

// register msj
const registerForm = document.getElementById('registerForm');
if (registerForm) {
    registerForm.addEventListener('submit', async function(event) {
        event.preventDefault(); 

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        try {
            const response = await fetch('http://localhost:8080/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({
                    email: email,
                    password: password
                })
            });

            const data = await response.json();
            const responseMessage = document.getElementById('responseMessage');

            if (response.ok) {
                responseMessage.innerHTML = `<p style="color: green;">${data.message}</p>`;
            } else {
                responseMessage.innerHTML = `<p style="color: red;">${data.message}</p>`;
            }
        } catch (error) {
            console.error('Error en la solicitud de registro:', error);
            document.getElementById('responseMessage').innerHTML = `<p style="color: red;">Error al conectarse al servidor</p>`;
        }
    });
}
