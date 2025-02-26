window.onload = function() {
    setTimeout(showQuestion, 3000); // Espera 3 segundos para mostrar la pregunta
};

function showQuestion() {
    hideAllSections();
    document.getElementById('animation').style.display = 'none';
    document.getElementById('question').style.display = 'block';
}

function showAskAgain() {
    hideAllSections();
    document.getElementById('askAgain').style.display = 'block';
    displayRandomImage();
}

function showSurprise() {
    hideAllSections();
    document.getElementById('surprise').style.display = 'block'; // Muestra la sorpresa
    disperseBackgroundImages(); // Agrega imágenes de fondo dispersas
}


function hideAllSections() {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.style.display = 'none';
    });
}

function displayRandomImage() {
    const images = [
        'recursos/imagen2.jpg',
        'recursos/imagen3.jpg',
        'recursos/imagen4.jpg'
        // Agrega aquí todas las rutas de las imágenes que quieras usar
    ];
    const randomIndex = Math.floor(Math.random() * images.length);
    document.getElementById('askAgain-img').src = images[randomIndex];
}

function disperseBackgroundImages() {
    const images = [
        'recursos/imagen2.jpg',
        'recursos/imagen3.jpg',
        'recursos/imagen4.jpg',
        'recursos/imagen5.jpg',
        'recursos/imagen6.jpg',
        'recursos/imagen7.jpg',
        'recursos/imagen8.jpg',
        'recursos/imagen9.jpg',
        'recursos/imagen10.jpg',
        'recursos/imagen11.jpg',
        'recursos/imagen12.jpg',
        'recursos/imagen13.jpg'

        // Agrega aquí todas las rutas de las imágenes que quieras usar
    ];
    const container = document.createElement('div');
    container.className = 'background-images';
    document.body.appendChild(container);

    images.forEach(src => {
        const img = document.createElement('img');
        img.src = src;
        img.style.top = `${Math.random() * 90}%`;
        img.style.left = `${Math.random() * 90}%`;
        container.appendChild(img);
    });
}

