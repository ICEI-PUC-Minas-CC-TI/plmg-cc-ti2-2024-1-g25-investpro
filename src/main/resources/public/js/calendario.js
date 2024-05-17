const monthSelect = document.getElementById("month-select");
const calendarBody = document.getElementById("calendar-body");
const expenseSelect = document.getElementById("expense-select");
const expenseDescription = document.getElementById("expense-description");
const expenseAmount = document.getElementById("expense-amount");
const addExpenseButton = document.getElementById("add-expense");
const totalExpensesElement = document.getElementById("total-expenses");

let selectedYear = new Date().getFullYear();
let expenses = {};
let expenseTotals = {
    lazer: 0,
    medico: 0,
    comida: 0,
    automovel: 0,
    casa: 0,
};

function generateCalendar(year, month) {
    calendarBody.innerHTML = '';

    const startDate = new Date(year, month, 1);
    const endDate = new Date(year, month + 1, 0);
    const currentMonth = startDate.getMonth();

    const numDaysInMonth = endDate.getDate();
    const firstDayOfWeek = startDate.getDay();

    let currentDate = new Date(startDate);
    currentDate.setDate(1 - firstDayOfWeek);

    for (let i = 0; i < 6; i++) { 
        const row = document.createElement('tr');

        for (let j = 0; j < 7; j++) { 
            const cell = document.createElement('td');
            const date = currentDate.getDate();

            if (currentDate.getMonth() !== currentMonth) {
                cell.textContent = ''; 
            } else {
                cell.textContent = date;
                const dateKey = `${currentDate.getFullYear()}-${currentDate.getMonth() + 1}-${date}`;

                if (expenses[dateKey]) {
                    const expenseInfo = document.createElement('div');
                    expenseInfo.className = 'expense-info';

                    for (const expense of expenses[dateKey]) {
                        const expenseItem = document.createElement('div');
                        expenseItem.style.backgroundColor = getExpenseColor(expense.type);
                        expenseItem.textContent = `Tipo: ${expense.type}, Valor: R$ ${expense.amount.toFixed(2)}`;
                        expenseInfo.appendChild(expenseItem);
                    }

                    cell.appendChild(expenseInfo);
                }

                cell.dataset.date = dateKey;
                cell.dataset.expenseType = 'empty';
                cell.classList.add('selectable');
                cell.addEventListener('click', () => addExpense(dateKey));
            }

            row.appendChild(cell);
            currentDate.setDate(date + 1);
        }

        calendarBody.appendChild(row);

        if (currentDate.getMonth() !== currentMonth || currentDate.getDate() > numDaysInMonth) {
            break; 
        }
    }

    updateTotalExpenses();
    createExpenseChart();
}

function addExpense(dateKey) {
    const type = expenseSelect.value;
    const description = expenseDescription.value;
    const amount = parseFloat(expenseAmount.value);

    if (!expenses[dateKey]) {
        expenses[dateKey] = [];
    }

    expenses[dateKey].push({ type, description, amount });

    
    expenseTotals = {
        lazer: 0,
        medico: 0,
        comida: 0,
        automovel: 0,
        casa: 0,
    };

    for (const date in expenses) {
        for (const expense of expenses[date]) {
            expenseTotals[expense.type] += expense.amount;
        }
    }

    generateCalendar(selectedYear, monthSelect.value);
}

function updateTotalExpenses() {
    let total = 0;

    for (const date in expenses) {
        for (const expense of expenses[date]) {
            total += expense.amount;
        }
    }

    totalExpensesElement.textContent = `Total gasto este mês: R$ ${total.toFixed(2)}`;
    document.getElementById("lazer-total").textContent = `Lazer: R$ ${expenseTotals.lazer.toFixed(2)}`;
    document.getElementById("medico-total").textContent = `Médico: R$ ${expenseTotals.medico.toFixed(2)}`;
    document.getElementById("comida-total").textContent = `Comida: R$ ${expenseTotals.comida.toFixed(2)}`;
    document.getElementById("automovel-total").textContent = `Automóvel: R$ ${expenseTotals.automovel.toFixed(2)}`;
    document.getElementById("casa-total").textContent = `Casa: R$ ${expenseTotals.casa.toFixed(2)}`;
}

function createExpenseChart() {
    const expenseChartCanvas = document.getElementById("expense-chart");
    const ctx = expenseChartCanvas.getContext("2d");

    const labels = Object.keys(expenseTotals);
    const data = Object.values(expenseTotals);

    const colors = labels.map(type => getExpenseColor(type));

    const config = {
        type: 'doughnut',
        data: {
            labels: labels,
            datasets: [{
                data: data,
                backgroundColor: colors,
                borderWidth: 1,
            }],
        },
        options: {
            plugins: {
                legend: {
                    position: 'bottom',
                },
            },
        },
    };

    if (window.expenseChart) {
        window.expenseChart.destroy();
    }

    window.expenseChart = new Chart(ctx, config);
}

monthSelect.addEventListener('change', () => {
    generateCalendar(selectedYear, monthSelect.value);
});

addExpenseButton.addEventListener('click', () => {
    const selectedDate = new Date(selectedYear, monthSelect.value, 1);
    addExpense(`${selectedDate.getFullYear()}-${selectedDate.getMonth() + 1}-${selectedDate.getDate()}`);
});

generateCalendar(selectedYear, monthSelect.value);

function getExpenseColor(expenseType) {
    switch (expenseType) {
        case "lazer":
            return "blue";
        case "medico":
            return "red";
        case "comida":
            return "green";
        case "automovel":
            return "orange";
        case "casa":
            return "purple";
        default:
            return null;
    }
}
