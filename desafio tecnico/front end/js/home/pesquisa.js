document.getElementById("search-form").addEventListener("submit", function (event) {
    event.preventDefault();

    const parametro = document.getElementById("parametro").value.trim();
    const resultsContainer = document.getElementById("results-container");

    resultsContainer.innerHTML = '';

    if (!parametro) {
        resultsContainer.innerHTML = "<p>Digite um nome para buscar.</p>";
        return;
    }

    fetch('http://localhost:8080/clientes/buscar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ parametro: parametro })
    })
    .then(response => {
        if (!response.ok) throw new Error('Erro na requisição');
        return response.json();
    })
    .then(clientesPage => {
        const clientes = clientesPage.content;

        if (clientes && clientes.length > 0) {
            clientes.forEach(pessoa => {
                const pessoaElement = document.createElement("div");
                pessoaElement.classList.add("result-item");

                // 🔥 Montagem do HTML exatamente como você pediu
                pessoaElement.innerHTML = `
                    <h2>${pessoa.nome}</h2>
                    <p class="info"><strong>CPF:</strong> ${pessoa.cpf}</p>
                    <p class="info"><strong>Nascimento:</strong> ${pessoa.nascimento || 'N/A'}</p>
                    <p class="info"><strong>Endereço:</strong> ${pessoa.endereco || 'N/A'}</p>
                    <div class="contatos">
                        <strong>Contatos:</strong>
                        ${pessoa.contatos && pessoa.contatos.length > 0 ? pessoa.contatos.map(c => `
                            <div class="contato">
                                ${c.tipo}: ${c.valor} ${c.observacao ? '(' + c.observacao + ')' : ''}
                                <a href="editar-contato.html?id=${c.id}">Editar Contato</a>
                                <button class="delete-contato-btn" onclick="deleteContato('${c.id}', this)">X</button>
                            </div>
                        `).join('') : '<p>Nenhum contato</p>'}
                    </div>
                `;
                
                resultsContainer.appendChild(pessoaElement);
            });
        } else {
            resultsContainer.innerHTML = "<p>Nenhum cliente encontrado.</p>";
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        resultsContainer.innerHTML = "<p>Erro ao buscar clientes. Tente novamente.</p>";
    });
});
