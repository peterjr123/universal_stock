$(document).ready(function() {
    // 서버에서 데이터 가져오기
    $.ajax({
        url: 'stock/price',
        method: 'GET',
        dataType: 'json',
        success: function(data) {
            // 시간과 주가 배열 생성
            const labels = data.map(item => item.time);
            const prices = data.map(item => item.price);

            // Chart.js를 이용해 차트 그리기
            const ctx = document.getElementById('stockChart').getContext('2d');
            const stockChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Stock Price',
                        data: prices,
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 2,
                        pointRadius: 5, // 포인트 크기
                        pointHoverRadius: 7 // 호버 시 포인트 크기
                    }]
                },
                options: {
                    onClick: function(event, elements) {
                        if (elements.length > 0) {
                            const index = elements[0].index;
                            const time = labels[index];
                            const price = prices[index];
                            alert(`Time: ${time}, Price: ${price}`);
                        }
                    },
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: 'Time'
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                text: 'Price'
                            }
                        }
                    }
                }
            });
        },
        error: function() {
            alert('Failed to load stock data');
        }
    });
});
