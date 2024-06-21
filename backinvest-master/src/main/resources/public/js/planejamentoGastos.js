
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    var controleGastos = JSON.parse(localStorage.getItem('controleGastos')) || {
      "gastos": {
        "lazerGastos": 0,
        "alimentacaoGastos": 0,
        "contasGastos": 0,
        "remediosGastos": 0,
        "aluguelGastos": 0
      },
      "futuro": {
        "lazerFuturo": 0,
        "alimentacaoFuturo": 0,
        "contasFuturo": 0,
        "remediosFuturo": 0,
        "aluguelFuturo": 0
      }
    };

    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ['Genre', 'Gastos', 'Valor disponível para gastar',{ role: 'annotation' } ],
        ['Lazer', controleGastos.gastos.lazerGastos, controleGastos.futuro.lazerFuturo, ''],
        ['Alimentação', controleGastos.gastos.alimentacaoGastos, controleGastos.futuro.alimentacaoFuturo, ''],
        ['Contas', controleGastos.gastos.contasGastos, controleGastos.futuro.contasFuturo, ''],
        ['Remédios', controleGastos.gastos.remediosGastos, controleGastos.futuro.remediosFuturo, ''],
        ['Aluguel', controleGastos.gastos.aluguelGastos, controleGastos.futuro.aluguelFuturo, ''],
      ]);

      var options = {
        width: 600,
        height: 400,
        legend: { position: 'top', maxLines: 3 },
        bar: { groupWidth: '75%' },
        isStacked: true
      };

      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }

    function salvarDados() {
      localStorage.setItem('controleGastos', JSON.stringify(controleGastos));
    }

    function inserirValoresProximoMes() {
      controleGastos.futuro.lazerFuturo = parseFloat(prompt('Digite quanto deseja gastar com lazer no próximo mês:') || 0);
      controleGastos.futuro.alimentacaoFuturo = parseFloat(prompt('Digite quanto deseja gastar com alimentação no próximo mês:') || 0);
      controleGastos.futuro.contasFuturo = parseFloat(prompt('Digite quanto deseja gastar com contas no próximo mês:') || 0);
      controleGastos.futuro.remediosFuturo = parseFloat(prompt('Digite quanto deseja gastar com remédios no próximo mês:') || 0);
      controleGastos.futuro.aluguelFuturo = parseFloat(prompt('Digite quanto deseja gastar com aluguel no próximo mês:') || 0);
      drawChart();
      salvarDados();
    }

    function alterarLazerGastos() {
      var novoValor = parseFloat(prompt('Digite o valor:') || 0);
      controleGastos.gastos.lazerGastos += novoValor;
      controleGastos.futuro.lazerFuturo -= novoValor;
      drawChart();
      salvarDados();
    }

    function alterarAlimentacaoGastos() {
      var novoValor = parseFloat(prompt('Digite o valor:') || 0);
      controleGastos.gastos.alimentacaoGastos += novoValor;
      controleGastos.futuro.alimentacaoFuturo -= novoValor;
      drawChart();
      salvarDados();
    }

    function alterarContasGastos() {
      var novoValor = parseFloat(prompt('Digite o valor:') || 0);
      controleGastos.gastos.contasGastos += novoValor;
      controleGastos.futuro.contasFuturo -= novoValor;
      drawChart();
      salvarDados();
    }

    function alterarRemediosGastos() {
      var novoValor = parseFloat(prompt('Digite o valor:') || 0);
      controleGastos.gastos.remediosGastos += novoValor;
      controleGastos.futuro.remediosFuturo -= novoValor;
      drawChart();
      salvarDados();
    }

    function alterarAluguelGastos() {
      var novoValor = parseFloat(prompt('Digite o valor:') || 0);
      controleGastos.gastos.aluguelGastos += novoValor;
      controleGastos.futuro.aluguelFuturo -= novoValor;
      drawChart();
      salvarDados();
    }