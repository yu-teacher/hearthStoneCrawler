// $.ajax({
//     type: 'post',           // 타입 (get, post, put 등등)    
//     url: '/test',           // 요청할 서버url    
//     async: true,            // 비동기화 여부 (default : true)    
//     headers: {              // Http header      
//         "Content-Type": "application/json", "X-HTTP-Method-Override": "POST"
//     },
//     dataType: 'text',       // 데이터 타입 (html, xml, json, text 등등)    
//     data: JSON.stringify({  // 보낼 데이터 (Object , String, Array)      
//         "no": no, "name": name, "nick": nick
//     }),
//     success: function (result) { // 결과 성공 콜백함수        
//         console.log(result);
//     },
//     error: function (request, status, error) { // 결과 에러 콜백함수        
//         console.log(error)
//     }
// })
let searchInput;
function searchGoogle() {
    searchInput = $('#searchInput').val();
    console.log("Google에서 검색: " + searchInput);

    let battleTag = '';

    if(undefined !== searchInput) {
        battleTag = '&battleTag='+searchInput;
    }

    ajaxForm('GET', '/tierList?pageNumber=' + 1 + battleTag, 'JSON',
        function (result) {
            innerBar(1, result);
            innerRanking(result);
        })
}

function pageMove(number, search) {
    let battleTag = '';

    if(undefined !== searchInput) {
        battleTag = '&battleTag='+searchInput;
    }
    ajaxForm('GET', '/tierList?pageNumber=' + number + battleTag, 'JSON',
        function (result) {
            innerBar(number, result);
            innerRanking(result);
        })
}

function roadPage() {
    ajaxForm('GET', '/tierList', 'JSON',
        function (result) {
            innerBar(1, result);
            innerRanking(result);
        })

}

function ajaxForm(type, url, dataType, success, error) {
    $.ajax({
        type: type,
        url: url,
        dataType: dataType,
        success: success,
        error: error,
    });
}

function innerRanking(result) {
    let innerText = '';

    for (let i = 0; i < result.numberOfElements; i++) {
        innerText +=
            `<tr>
                <td>${result.content[i].userRanking}</td>
                <td>${result.content[i].battleTag}</td>
                <td>${result.content[i].avgWins}</td>
            </tr>`;
    }
    $('.ranking').html(innerText);
}

function innerBar(number, result) {
    let maxPage = result.totalPages;
    let innerNumber = '';

    if (number <= 3) {
        innerNumber +=
            `<ul class="navigation-bar">
                    <li class="navigation-item"> 
                        <button onclick ="pageMove(1);" class="navigation-link">1</button>
                    </li>
                    <li class="navigation-item"> 
                        <button onclick ="pageMove(2);" class="navigation-link">2</button>
                    </li>
                    <li class="navigation-item">
                        <button onclick ="pageMove(3);" class="navigation-link">3</button>
                    </li>
                    <li class="navigation-item">
                        <button onclick ="pageMove(4);" class="navigation-link">4</button>
                    </li>
                    <li class="navigation-item"> 
                        <button onclick ="pageMove(5);" class="navigation-link">5</button>
                    </li> 
             </ul>`;

        $('.navigation-bar').html(innerNumber);
    } else if (number >= maxPage - 2) {
        innerNumber +=
            `<ul class="navigation-bar">
                    <li class="navigation-item"> 
                        <button onclick ="pageMove(${maxPage - 4});" class="navigation-link">${maxPage - 4}</button>
                    </li>
                    <li class="navigation-item"> 
                        <button onclick ="pageMove(${maxPage - 3});" class="navigation-link">${maxPage - 3}</button>
                    </li>
                    <li class="navigation-item">
                        <button onclick ="pageMove(${maxPage - 2});" class="navigation-link">${maxPage - 2}</button>
                    </li>
                    <li class="navigation-item">
                        <button onclick ="pageMove(${maxPage - 1});" class="navigation-link">${maxPage - 1}</button>
                    </li>
                    <li class="navigation-item"> 
                        <button onclick ="pageMove(${maxPage});" class="navigation-link">${maxPage}</button>
                    </li> 
             </ul>`;

        $('.navigation-bar').html(innerNumber);
    } else if (number => 3 && number < maxPage - 2) {
        innerNumber +=
            `<ul class="navigation-bar">
                    <li class="navigation-item"> 
                        <button onclick ="pageMove(${number - 2});" class="navigation-link">${number - 2}</button>
                    </li>
                    <li class="navigation-item"> 
                        <button onclick ="pageMove(${number - 1});" class="navigation-link">${number - 1}</button>
                    </li>
                    <li class="navigation-item">
                        <button onclick ="pageMove(${number});" class="navigation-link">${number}</button>
                    </li>
                    <li class="navigation-item">
                        <button onclick ="pageMove(${number + 1});" class="navigation-link">${number + 1}</button>
                    </li>
                    <li class="navigation-item"> 
                        <button onclick ="pageMove(${number + 2});" class="navigation-link">${number + 2}</button>
                    </li> 
             </ul>`;

        $('.navigation-bar').html(innerNumber);
    }
}