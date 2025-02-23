google.charts.load("current", {packages:["corechart", "line"]});
google.charts.setOnLoadCallback(initializeCharts);

let cpuData, memoryData;

function initializeCharts() {
    cpuData = new google.visualization.DataTable();
    cpuData.addColumn('datetime', 'Time');
    cpuData.addColumn('number', 'CPU Usage');

    memoryData = new google.visualization.DataTable();
    memoryData.addColumn('datetime', 'Time');
    memoryData.addColumn('number', 'Memory Usage');

    fetchAndUpdateCharts();
}

function fetchAndUpdateCharts() {
    fetch(apiUrl)  // Call Spring Boot API
        .then(response => response.json())
        .then(data => {
        let currentTime = new Date();

        // Update Doughnut Charts
        drawDoughnutChart("cpu-chart", "CPU Usage", data.cpu_usage_percentage, 100 - data.cpu_usage_percentage);
        drawDoughnutChart("memory-chart", "Memory Usage", data.memory_usage.used_mb, data.memory_usage.free_mb);
        drawDoughnutChart("disk-chart", "Disk Usage", data.disk_usage.used_mb, data.disk_usage.free_mb);

        // Update Line Chart Data
        cpuData.addRow([currentTime, data.cpu_usage_percentage]);
        memoryData.addRow([currentTime, data.memory_usage.used_mb]);

        // Keep only last 20 data points for smooth animation
        if (cpuData.getNumberOfRows() > 20) {
            cpuData.removeRow(0);
            memoryData.removeRow(0);
        }

        drawLineChart("cpu-line-chart", "CPU Usage Over Time", cpuData, "CPU Usage (%)");
        drawLineChart("memory-line-chart", "Memory Usage Over Time", memoryData, "Memory Used (MB)");
    })
        .catch(error => console.error("Error fetching data:", error));

    setTimeout(fetchAndUpdateCharts, 5000); // Auto-refresh every 5 seconds
}

// Function to draw Doughnut Charts
function drawDoughnutChart(containerId, title, used, free) {
    var data = google.visualization.arrayToDataTable([
        ['Type', 'Value'],
        ['Used', used],
        ['Free', free]
    ]);

    var options = {
        title: title,
        pieHole: 0.4,
        colors: ['#FF6384', '#36A2EB']
    };

    var chart = new google.visualization.PieChart(document.getElementById(containerId));
    chart.draw(data, options);
}

// Function to draw Line Charts
function drawLineChart(containerId, title, dataTable, yAxisLabel) {
    var options = {
        title: title,
        curveType: 'function',
        legend: { position: 'bottom' },
        hAxis: { title: 'Time', format: 'HH:mm:ss' },
        vAxis: { title: yAxisLabel },
        colors: ['#FF8C00']
    };

    var chart = new google.visualization.LineChart(document.getElementById(containerId));
    chart.draw(dataTable, options);
}
