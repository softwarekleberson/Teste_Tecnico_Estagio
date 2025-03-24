document.getElementById("formCliente").addEventListener("submit", async function (event) {
    event.preventDefault(); 

    const nome = document.getElementById("nomme").value.trim();
    const cpf = document.getElementById("cpf").value.trim();
    const dataNascimento = document.getElementById("dataNascimento").value.trim();
    const endereco = document.getElementById("endereco").value.trim();

    if (!nome || !cpf) {
        document.getElementById("mensagem").textContent = "Nome e CPF são obrigatórios!";
        return;
    }

    const cliente = { nome, cpf };
    
    if (dataNascimento) cliente.dataNascimento = dataNascimento;
    if (endereco) cliente.endereco = endereco;

    try {
        const response = await fetch("http://localhost:8080/clientes", { 
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(cliente)
        });

        if (response.ok) {
            document.getElementById("mensagem").textContent = "Cliente cadastrado com sucesso!";
            
            setTimeout(() => {
                document.getElementById("formCliente").reset();
                document.getElementById("mensagem").textContent = ""; 
            }, 3000);
            
        } else {
            const errorData = await response.json();
            document.getElementById("mensagem").textContent = errorData.message || "Erro ao cadastrar cliente.";
        }
    } catch (error) {
        document.getElementById("mensagem").textContent = "Erro ao conectar com o servidor.";
        console.error("Erro:", error);
    }
});
