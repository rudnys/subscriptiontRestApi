	function makeAjaxCall(urlStr,reqMethod,headers,displayElement,dataVal) {
		
		$.ajax({
			async: false,
			url : urlStr,
			type :reqMethod,
			dataType : 'json',
			data :  JSON.stringify(dataVal),
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			success : function(data, status, xhttp) {
				createView(data,headers,displayElement);
			},
			error : function(xhr, status, error) {
				//var err = JSON.parse(xhr.responseText);
				var responseText = jQuery.parseJSON(xhr.responseText);
				console.log(xhr);
			}
		});

	}
	function createView(data,headers,displayElement) {
		if(!(data instanceof Array)){
			$('#'+displayElement+'s').hide();
			$('#'+displayElement+'e').hide();			
			$('#'+displayElement).html('');
			if(data){
				$('#'+displayElement+'s').show();
			}
			else{
				$('#'+displayElement+'e').show();
			}
			return;
		}
		
		
		content = '<table class="table table-bordered table-hover table-striped" > ';
		content += '<thead><tr>';
		$.each(headers, function(o, head) {
			content += '<th>' + head + '</th>';
			
		});
		content += '</tr></thead>';
		$.each(data, function(i, product) {
			
			content += '<tr>';
			$.each(product, function(o, field) {
				content += '<td>' + field + '</td>';
			});
			content += '</tr>';
		});
		content += '</table>';
		$('#'+displayElement).html(content);
	}
	
	function populateSelect(urlStr,fieldList,displayElement){
		
		$.ajax({
			url : urlStr,
			type :'GET',
			dataType : 'json',
			success : function(data, status, xhttp) {
				content='';
				
				$.each(data, function(i, product) {
					content+=' <option value="'+product[fieldList[0]]+'">'+product[fieldList[1]]+'</option>'
				});
				
				$('#'+displayElement).html(content);
			},
			error : function() {
				alert('error');//error condition code
			}
		});
		
		
		
	}
	
	