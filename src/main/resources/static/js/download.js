// 클릭한 테이블도 엑셀저장 가능
// 클라이언트 측 JavaScript
document.addEventListener('DOMContentLoaded', () => {
    // 엑셀로 내보내기 버튼 클릭 이벤트 처리
    const exportButton = document.querySelector('#fileSave');
    exportButton.addEventListener('click', exportToExcel);

    // 전체 선택 버튼 클릭 이벤트 처리
    const allSelectButton = document.querySelector('.allSelect');
    allSelectButton.addEventListener('click', selectAllRows);
});

//전체버튼 클릭
function selectAllRows() {
    const table = document.querySelector('.orderData');
    const checkboxes = table.querySelectorAll('input[type="checkbox"]');
    const allSelectButton = document.querySelector('.allSelect');

    const isChecked = allSelectButton.classList.toggle('selected');

    checkboxes.forEach(checkbox => {
        checkbox.checked = isChecked;
        const row = checkbox.closest('tr');
        changeColor(row, isChecked);
    });

    // 전체 선택 버튼을 다시 눌렀을 때 원래 색상으로 돌아오도록 처리
    if (!isChecked) {
        resetRowColors(table);
    }
}

function changeColor(row, isChecked) {
    if (isChecked) {
        row.style.backgroundColor = '#DDEAFD';
    } else {
        row.style.backgroundColor = ''; // 기본값으로 돌아가도록 함
    }
}

function resetRowColors(table) {
    const rows = table.querySelectorAll('tr');
    rows.forEach(row => {
        row.style.backgroundColor = ''; // 기본값으로 돌아가도록 함
    });
}

function exportToExcel() {
    // 선택한 행 데이터 추출
    const selectedRows = getSelectedRows();

    // 선택한 행 데이터로 엑셀 워크시트 생성
    const worksheet = createWorksheet(selectedRows);

    // 워크시트를 워크북에 추가
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, 'Shipment Data');

    // 엑셀 파일 생성 및 다운로드
    const excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    saveAsExcelFile(excelBuffer, 'shipment_data.xlsx');
}

function getSelectedRows() {
    const table = document.querySelector('.orderData');
    const checkboxes = table.querySelectorAll('input[type="checkbox"]:checked');
    const selectedRows = Array.from(checkboxes).map(checkbox => checkbox.closest('tr'));
    return selectedRows;
}

function createWorksheet(rows) {
    const worksheet = XLSX.utils.aoa_to_sheet([]);
    const header = ['출하번호', '출하일자', '상품번호']; // 헤더 데이터 배열
    XLSX.utils.sheet_add_aoa(worksheet, [header], { origin: 'A1' });

    rows.forEach((row, index) => {
        const rowData = [];
        const cells = row.querySelectorAll('td');
        cells.forEach(cell => {
            rowData.push(cell.innerText);
        });
        XLSX.utils.sheet_add_aoa(worksheet, [rowData], { origin: `A${index + 2}` });
    });

    return worksheet;
}

function saveAsExcelFile(buffer, fileName) {
    const data = new Blob([buffer], { type: 'application/octet-stream' });
    saveAs(data, fileName);
}





