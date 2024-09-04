document.getElementById("drop-area").addEventListener("click", function () {
    document.getElementById("fileElem").click();
});

document.getElementById("fileElem").addEventListener("change", handleFiles);

["dragenter", "dragover", "dragleave", "drop"].forEach(eventName => {
    document.getElementById("drop-area").addEventListener(eventName, preventDefaults, false);
});

function preventDefaults(e) {
    e.preventDefault();
    e.stopPropagation();
}

document.getElementById("drop-area").addEventListener("drop", handleDrop, false);

function handleDrop(e) {
    const dt = e.dataTransfer;
    const files = dt.files;
    handleFiles({ target: { files } });
}

function handleFiles(e) {
    const files = e.target.files;
    const gallery = document.getElementById("gallery");
    gallery.innerHTML = ""; // Limpa o conteúdo atual

    [...files].forEach(file => {
        const img = document.createElement("img");
        img.src = URL.createObjectURL(file);
        img.onload = () => URL.revokeObjectURL(img.src); // Libera memória
        gallery.appendChild(img);
    });
}

document.querySelectorAll('.dropdown-item').forEach(item => {
            item.addEventListener('click', event => {
                event.preventDefault(); // Evita o comportamento padrão do link

                // Obtém o texto e o valor do item selecionado
                const selectedItemText = event.target.textContent;
                const selectedItemValue = event.target.getAttribute('data-value');

                // Atualiza o texto do botão dropdown
                const dropdownButton = document.getElementById('dropdownMenuButton');
                dropdownButton.textContent = selectedItemText;

                // Atualiza o valor do input oculto
                const hiddenInput = document.getElementById('tipo-imovel');
                hiddenInput.value = selectedItemValue;
            });
        });