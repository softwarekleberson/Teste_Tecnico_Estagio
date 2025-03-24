const urlParams = new URLSearchParams(window.location.search);
const contatoId = urlParams.get('id');
const apiUrl = `http://localhost:8080/contatos/${contatoId}`;

fetch(apiUrl)
  .then(response => response.json())
  .then(data => {
    document.getElementById('valor').value = data.valor;
    document.getElementById('observacao').value = data.observacao || '';
  })
  .catch(error => {
    alert('Erro ao carregar o contato');
    console.error(error);
  });

document.getElementById('contatoForm').addEventListener('submit', function(e) {
  e.preventDefault();

  const contatoAtualizado = {
    valor: document.getElementById('valor').value,
    observacao: document.getElementById('observacao').value
  };

  fetch(apiUrl, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(contatoAtualizado)
  })
  .then(response => {
    if (response.ok) {
      alert('Contato atualizado com sucesso!');
      window.location.href = 'index.html';
    } else {
      alert('Erro ao atualizar o contato.');
    }
  })
  .catch(error => {
    alert('Erro ao atualizar o contato.');
    console.error(error);
  });
});