fetch('http://localhost:8080/clientes')
    .then(response => response.json())
    .then(data => {
        data.content.forEach(pessoa => {
            const card = document.createElement('div');
            card.className = 'card';
            card.innerHTML = `
                <button class="delete-btn" onclick="deleteCliente('${pessoa.id}', this)">X</button>
                <a href="editar-cliente.html?id=${pessoa.id}">Editar Cliente</a>
                <h2>${pessoa.nome}</h2>
                <p class="info"><strong>CPF:</strong> ${pessoa.cpf}</p>
                <p class="info"><strong>Nascimento:</strong> ${pessoa.nascimento || 'N/A'}</p>
                <p class="info"><strong>Endereço:</strong> ${pessoa.endereco || 'N/A'}</p>
                <div class="contatos">
                    <strong>Contatos:</strong>
                    ${pessoa.contatos.length > 0 ? pessoa.contatos.map(c => `
                        <div class="contato">
                            ${c.tipo}: ${c.valor} ${c.observacao ? '(' + c.observacao + ')' : ''}
                            <a href="editar-contato.html?id=${c.id}">Editar Contato</a>
                            <button class="delete-contato-btn" onclick="deleteContato('${c.id}', this)">X</button>
                        </div>
                    `).join('') : '<p>Nenhum contato</p>'}
                </div>
            `;
            document.body.appendChild(card);
        });
    })
    .catch(error => console.error('Erro ao buscar os dados:', error));

function deleteCliente(id, button) {
    if (confirm("Tem certeza que deseja excluir este cliente?")) {
        fetch(`http://localhost:8080/clientes/${id}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    button.parentElement.remove();
                } else {
                    alert('Erro ao deletar cliente');
                }
            })
            .catch(error => console.error('Erro ao deletar cliente:', error));
    }
}

function deleteContato(id, button) {
    if (confirm("Tem certeza que deseja excluir este contato?")) {
        fetch(`http://localhost:8080/contatos/${id}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    button.parentElement.remove();
                } else {
                    alert('Erro ao deletar contato');
                }
            })
            .catch(error => console.error('Erro ao deletar contato:', error));
    }
}
