const urlParams = new URLSearchParams(window.location.search);
    const clienteId = urlParams.get('id');
    const apiUrl = `http://localhost:8080/clientes/${clienteId}`;

    fetch(apiUrl)
      .then(response => response.json())
      .then(data => {
        document.getElementById('nome').value = data.nome;
        document.getElementById('dataNascimento').value = data.nascimento || '';
        document.getElementById('endereco').value = data.endereco || '';
      });

    document.getElementById('clienteForm').addEventListener('submit', function(e) {
      e.preventDefault();

      const clienteAtualizado = {
        nome: document.getElementById('nome').value,
        dataNascimento: document.getElementById('dataNascimento').value,
        endereco: document.getElementById('endereco').value
      };

      fetch(apiUrl, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(clienteAtualizado)
      })
      .then(response => {
        if (response.ok) {
          alert('Cliente atualizado com sucesso!');
          window.location.href = 'index.html';
        } else {
          alert('Erro ao atualizar o cliente.');
        }
      });
    });