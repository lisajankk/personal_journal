const imageInput = document.getElementById('imageInput');
const previewImage = document.getElementById('previewImage');
const saveButton = document.getElementById('saveButton');
const linkInput = document.getElementById('link');

imageInput.addEventListener('change', (e) => {
    const file = e.target.files[0];
    const reader = new FileReader();

    reader.onload = function (e) {
        previewImage.src = e.target.result;
        saveButton.disabled = false;
    }

    reader.readAsDataURL(file);
});

saveButton.addEventListener('click', () => {
    const canvas = document.createElement('canvas');
    const context = canvas.getContext('2d');
    canvas.width = previewImage.naturalWidth;
    canvas.height = previewImage.naturalHeight;
    context.drawImage(previewImage, 0, 0);

    const link = document.createElement('a');
    link.href = canvas.toDataURL('image/png');
    link.download = linkInput.value + '.png';
    link.click();
});