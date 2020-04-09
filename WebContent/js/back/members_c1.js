var numCount; // 資料總數量
var columnsCounts; // 資料列數量
var pageCount; // 每頁顯示的數量
var pageNum; // 總頁數
var currPageNum; // 當前頁數

// 頁面標籤變數
var blockTable;
var preSpan;
var firstSpan;
var nextSpan;
var lastSpan;
var pageNumSpan;
var currPageSpan;
/** 首頁* */
function firstPage() {
	hide();
	currPageNum = 1;
	showCurrPage(currPageNum);
	showTotalPage();
	
	for (var i = 1; i < pageCount +1; i++) {
		blockTable.rows[i].style.display = "";
	}
	if(pageNum>1){
		firstText();
		preText();
		nextLink();
		lastLink();
	}
}
/** 前一頁* */
function prePage() {
	hide();
	currPageNum--;
	showCurrPage(currPageNum);
	showTotalPage();
	var firstR = firstRow(currPageNum);
	var lastR = lastRow(firstR);
	for (var i = firstR; i < lastR; i++) {
		blockTable.rows[i].style.display = "";
	}

	if (1 == currPageNum) {
		firstText();
		preText();
		nextLink();
		lastLink();
	} else if (pageNum == currPageNum) {
		preLink();
		firstLink();
		nextText();
		lastText();
	} else {
		firstLink();
		preLink();
		nextLink();
		lastLink();
	}

}
/** 下一頁* */
function nextPage() {
	hide();
	currPageNum++;
	showCurrPage(currPageNum);
	showTotalPage();
	var firstR = firstRow(currPageNum);
	var lastR = lastRow(firstR);
	for (var i = firstR; i < lastR; i++) {
		blockTable.rows[i].style.display = "";
	}

	if (1 == currPageNum) {
		firstText();
		preText();
		nextLink();
		lastLink();
	} else if (pageNum == currPageNum) {
		preLink();
		firstLink();
		nextText();
		lastText();
	} else {
		firstLink();
		preLink();
		nextLink();
		lastLink();
	}
}
/** 尾頁* */
function lastPage() {
	hide();
	currPageNum = pageNum;
	showCurrPage(currPageNum);
	showTotalPage();
	var firstR = firstRow(currPageNum);
	for (var i = firstR; i < numCount + 1; i++) {
		blockTable.rows[i].style.display = "";
	}

	firstLink();
	preLink();
	nextText();
	lastText();
}

// 計算將要顯示的頁面的首行和尾行
function firstRow(currPageNum) {
	return pageCount * (currPageNum - 1) + 1;
}

function lastRow(firstRow) {
	var lastRow = firstRow + pageCount;
	if (lastRow > numCount + 1) {
		lastRow = numCount + 1;
	}
	return lastRow;
}

function showCurrPage(cpn) {
	currPageSpan.innerHTML = cpn;
}

function showTotalPage() {
	
}
// 隱藏所有行
function hide() {
	for (var i = 1; i < numCount + 1; i++) {
		blockTable.rows[i].style.display = "none";
	}
}

// 控制首頁等功能的顯示與不顯示
function firstLink() {
	firstSpan.disabled="";
	
}
function firstText() {
	firstSpan.disabled="disabled";
	
}

function preLink() {
	preSpan.disabled="";
}
function preText() {
	preSpan.disabled="disabled";
}

function nextLink() {
	nextSpan.disabled="";
}
function nextText() {
	nextSpan.disabled="disabled";
}

function lastLink() {
	lastSpan.disabled="";
}
function lastText() {
	lastSpan.disabled="disabled";
}
