var chartDataStr = decodeHTML(chartData);
var chartJsonArray = JSON.parse(chartDataStr);

var numericData = [];
var labelData = [];

for(var i=0; i< chartJsonArray.length;i++){
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}


  const data = {
    labels: labelData,
    datasets: [{
      label: 'My First dataset',
      backgroundColor: ["red", "blue", "green"],
      borderColor: 'rgb(255, 99, 132)',
      data: numericData,
    }]
  };
  
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    data: data,
    options: {
	title: {
		display: true,
		text: "Project Statuses"
	}
}
});

function decodeHTML(html) {
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}