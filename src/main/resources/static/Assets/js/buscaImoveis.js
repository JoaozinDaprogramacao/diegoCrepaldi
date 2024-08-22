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


 document.getElementById("toggleBtn").addEventListener("click", function () {
            const formFilters = document.getElementById("formFilters");
            const arrow = document.getElementById("arrow");

            // Alterna a visibilidade da div de filtros
            formFilters.style.display = formFilters.style.display === "none" ? "block" : "none";

            // Alterna a rotação da seta
            arrow.classList.toggle("rotate");
        });