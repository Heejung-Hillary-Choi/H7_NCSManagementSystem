//pdf 저장 
$(document).ready(function() {
	$('#savePdf').click(function() { // pdf저장 button id
		
	    html2canvas($('section')[0]).then(function(canvas) { //저장 영역 div id

	    //기본 변수 설정	
		var filename=$('h3').text(); //
		var padding = 15; //여백으로 사용할 변수 설정
	    
	    // 캔버스를 이미지로 변환
	    var imgData = canvas.toDataURL('image/png');
		
	    //A4용지 기준으로 이미지 크기 렌더링
	    var imgWidth = 210; // 이미지 가로 길이(mm) / A4 기준 210mm
	    var imgHeight = canvas.height * imgWidth / canvas.width;

//	    var pageWidth = imgWidth; 이해도를 높이기 위해, 우선 적어두고, 주석처리 합니다.
	    var pageHeight = 297;  // 출력 페이지 세로 길이 계산 A4 기준
	    
	    var heightLeft = imgHeight;	    
	    var margin = 0; // 출력 페이지 왼쪽 시작점
	    var position = padding; //상단 시작점
	    
	    var doc = new jsPDF('p', 'mm');
	       
	    // 첫 페이지 출력
	    doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight);
	    heightLeft=heightLeft-pageHeight+padding*2;
	         
	    // 한 페이지 이상일 경우 루프 돌면서 출력
	    while (heightLeft >= 0) {
	        position = heightLeft-imgHeight;
	        doc.addPage();
	        doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
	        heightLeft -= pageHeight;
	    }
	 
	    // 파일 저장
	    doc.save(filename+'.pdf');

		  
	});

	});
	
	
})
//-------------------------------------------------------------------------
//인쇄버튼
var initBody;
function beforePrint()
{ 
   initBody = document.body.innerHTML; 
   document.body.innerHTML = sec.innerHTML;
} 

function afterPrint()
{ 
  document.body.innerHTML = initBody; 
} 

function pageprint()
{
     window.onbeforeprint = beforePrint; 
     window.onafterprint = afterPrint; 
     window.print(); 
}