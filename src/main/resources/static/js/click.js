function toggleCheckbox(row) {
  const checkbox = row.querySelector('input[type="checkbox"]');
  checkbox.checked = !checkbox.checked;
  if (checkbox.checked) {
    row.style.backgroundColor = '#DDEAFD';
  } else {
    row.style.backgroundColor = ''; // 기본값으로 돌아가도록 함
  }
}

function changeColor(row) {
  row.style.backgroundColor = '#DDEAFD';
}