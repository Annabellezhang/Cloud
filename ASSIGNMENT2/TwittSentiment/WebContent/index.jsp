<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="TwitterGMap.DataHelper,java.util.*"%>
<%@ page import="TwitterGMap.Tweets"%>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils"%>
<!DOCTYPE html>
<html>
<head>
<title>Twitter Sentiment</title>
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDS2ttROIC1WVD78wDT0R5VVJ5rXsppu9M&sensor=true&libraries=visualization"></script>
<script>
	<% DataHelper dh = new DataHelper();
		ArrayList<Tweets> list = dh.getAllData();
		int datanum = list.size(); %>
	var mapdata = [
	<% for (int i=0; i<datanum-1;i++) {
		Tweets t = list.get(i);
		String username = StringEscapeUtils.escapeEcmaScript(t.getUsername());
		String text = StringEscapeUtils.escapeEcmaScript(t.getText());
		//String sentiment = StringEscapeUtils.escapeEcmaScript(t.getSentiment());
		out.println("['"+username+"', '"+text+"', "+t.getLatitude()+", "+t.getLongtitude()+", '"+t.getKeyword()+"', '"+t.getSentiment()+"', '"+t.getTimestamp()+"'],");
	}
		Tweets t = list.get(datanum-1);
		String username = StringEscapeUtils.escapeEcmaScript(t.getUsername());
		String text = StringEscapeUtils.escapeEcmaScript(t.getText());
		//String sentiment = StringEscapeUtils.escapeEcmaScript(t.getSentiment());
		out.println("['"+username+"', '"+text+"', "+t.getLatitude()+", "+t.getLongtitude()+", '"+t.getKeyword()+"', '"+t.getSentiment()+"', '"+t.getTimestamp()+"'],");
	%>
	
	];
    var map, heatmap;
    var markers = [];
    var heatlocs = [];
    function initialize() {
      var mapOptions = {
        zoom: 2,
        center: new google.maps.LatLng(40.7127, -74.0059)
      };
      map = new google.maps.Map(document.getElementById('map-canvas'),
          mapOptions);
      var infowindow = new google.maps.InfoWindow();
 
      var i, marker;
      for (i = 0;i<mapdata.length;i++) {
      	marker = new google.maps.Marker({
      		position: new google.maps.LatLng(mapdata[i][2], mapdata[i][3]),
      		Icon:'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
      		map: map
      	});
      	if(mapdata[i][5]=="positive"){
            markers[i] = marker.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png');
        } else if(mapdata[i][5]=="negative") {
            markers[i] = marker.setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png');
        } else {
            markers[i] = marker;
        }
      	heatlocs[i] = new google.maps.LatLng(mapdata[i][2], mapdata[i][3]);    	
      	     	  	
      	google.maps.event.addListener(marker, 'click', (function(marker, i) {
      		return function() {
      			var contentstr = '<div>'
      			+'<h2> @'+unescape(mapdata[i][0])+' :</h2>'
      			+'<p>"'+unescape(mapdata[i][1])+'"</p>'
      			+'<p>At '+mapdata[i][5]+'</p>'
      			+'</div>';
      			infowindow.setContent(contentstr);
      			infowindow.open(map,marker);
      		}
      	})(marker, i));
      }
      var pointArray = new google.maps.MVCArray(heatlocs);
      heatmap = new google.maps.visualization.HeatmapLayer({
    	    data: pointArray
    	  });
      heatmap.set('radius', 20);
      heatmap.setMap(map);
      setAllMap(map);
    }
    
    function setAllMap(map) {
    	for (var i = 0; i < markers.length; i++) {
    		markers[i].setMap(map);
    	}
    }
        
    function changeKeyword(sel) {
    	setAllMap(null);
    	if(sel.value=="0")
    		setAllMap(map);
    	else{
        	for (var i = 0; i < markers.length; i++) {
        		if (sel.value == mapdata[i][4])
        			markers[i].setMap(map);
        	}
    	}
    }

    google.maps.event.addDomListener(window, 'load', initialize);
    
    </script>
<style>
#header {
	font-family: Cursive;
	color: black;
	text-align: center;
	padding: 5px;
}

#nav {
	font-family: Cursive;
	line-height: 30px;
	background-color: #eeeeee;
	height: 30px;
	float: center;
	padding: 5px;
}

#section {
	float: center;
	padding: 10px;
}

#footer {
	font-family: Cursive;
	color: black;
	clear: both;
	text-align: center;
	padding: 5px;
}
</style>
</head>
<body>
	<div id="header">
		<h1>Twitter Sentiment</h1>
	</div>
	
	<div id="nav">
		Team: Chenyun Zhang(cz574), Haoran Liu(hl1468), Yongqing Peng(yp639), Huansong Wang(hw977) 
		&nbsp;&nbsp;&nbsp;	
	</div>
	<div id="section">
		<div id="map-canvas" style="width:1000px;height:500px;"></div>
	</div>
	<div id="footer">
		AssignmentTwo for Cloud Computing cz574 & hl1468 & yp639 & hw977
	</div>
	
</body>
</html>