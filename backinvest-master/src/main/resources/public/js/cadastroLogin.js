function imprimeDados() {
    let tela = document.getElementById('tela');
    let strHtml = '';
    let objDados = leDados();
  
    for (i = 0; i < objDados.cadastros.length; i++) {
      strHtml += `<p>${objDados.cadastros[i].nome} - ${objDados.cadastros[i].sobrenome} - ${objDados.cadastros[i].email} -${objDados.cadastros[i].cpf}- ${objDados.cadastros[i].senha}</p>`
    }
  
    tela.innerHTML = strHtml;
  }

  /*function incluirCadastro(e) {
    e.preventDefault();

  let strEmail = document.getElementById('email').value;
  let strPassword = document.getElementById('passwordconfirmation').value;



  console.log(strEmail)
  console.log(strPassword)

  }

  document.getElementById('btn-submit').addEventListener('click', incluirCadastro);*/