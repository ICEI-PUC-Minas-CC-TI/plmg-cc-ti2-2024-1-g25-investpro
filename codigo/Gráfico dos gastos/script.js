google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawChart);

var gastos = JSON.parse(localStorage.getItem('gastos')) || {
  "lazerValor": 0,
  "alimentacaoValor": 0,
  "contasValor": 0,
  "remediosValor": 0,
  "aluguelValor": 0
};

function drawChart() {
  var data = google.visualization.arrayToDataTable([
    ['Categoria', 'Valor'],
    ['Lazer',     gastos.lazerValor],
    ['Alimentação',      gastos.alimentacaoValor],
    ['Contas',  gastos.contasValor],
    ['Remédios', gastos.remediosValor],
    ['Aluguel',    gastos.aluguelValor]
  ]);

  var options = {
    title: 'Gráfico dos gastos',
    pieHole: 0.4,
  };

  var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
  chart.draw(data, options);
}

function updateLazerValor() {
  gastos.lazerValor = parseInt(prompt("Alterar o valor dos gastos com lazer:"));
  salvarDados();
  drawChart();
}

function updateAlimentacaoValor() {
  gastos.alimentacaoValor = parseInt(prompt("Alterar o valor dos gastos com alimentação:"));
  salvarDados();
  drawChart();
}

function updateContasValor() {
  gastos.contasValor = parseInt(prompt("Alterar o valor dos gastos com contas:"));
  salvarDados();
  drawChart();
}

function updateRemediosValor() {
  gastos.remediosValor = parseInt(prompt("Alterar o valor dos gastos com remédios:"));
  salvarDados();
  drawChart();
}

function updateAluguelValor() {
  gastos.aluguelValor = parseInt(prompt("Alterar o valor dos gastos com aluguel:"));
  salvarDados();
  drawChart();
}

function salvarDados() {
  localStorage.setItem('gastos', JSON.stringify(gastos));
}
