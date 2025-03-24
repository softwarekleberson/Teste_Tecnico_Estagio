document.getElementById("formContato").addEventListener("submit", async function (event) {
    event.preventDefault(); 

    const cpf = document.getElementById("cpf").value.trim();
    const valor = document.getElementById("valor").value.trim();
    let observacao = document.getElementById("observacao").value.trim();

    observacao = observacao ? observacao : null;

    const contato = { cpf, valor, observacao };

    try {
        const response = await fetch("http://localhost:8080/contatos", { 
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(contato)
        });

        if (response.ok) {
            document.getElementById("mensagem").textContent = "Contato cadastrado com sucesso!";
            
            setTimeout(() => {
                document.getElementById("formContato").reset();
                document.getElementById("mensagem").textContent = ""; 
            }, 3000);
            
        } else {
            const errorData = await response.json();
            document.getElementById("mensagem").textContent = errorData.message || "Erro ao cadastrar contato.";
        }
    } catch (error) {
        document.getElementById("mensagem").textContent = "Erro ao conectar com o servidor.";
        console.error("Erro:", error);
    }
});
