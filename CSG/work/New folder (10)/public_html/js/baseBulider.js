var boolHome;
var boolSyllabus;
var boolSchedule;
var boolHws;
var boolProject;
function initBase(){
    var dataFile = "./data/SiteSaveTestExport.json";
    loadBaseData(dataFile);
    
}

function loadBaseData(jsonFile){
     $.getJSON(jsonFile, function (json) {
	initNavBar(json);
    initCourse(json);
    initcsspic(json);
        
    });

}

function initcsspic(data){

var index = data.banner_image .lastIndexOf("\\");  
var str  = data.banner_image .substring(index + 1, data.banner_image .length);
   $("#bannerpic").attr("src",".//images//"+str);

    var index = data.banner_image .lastIndexOf("\\");  
var str  = data.banner_image .substring(index + 1, data.banner_image .length);
    $("#leftpic").attr("src",".//images//"+str);

var index = data.banner_image .lastIndexOf("\\");  
var str  = data.banner_image .substring(index + 1, data.banner_image .length);
    $("#rightpic").attr("src",".//images//"+str);

    $("#csssheet").attr("href",".//css//"+data.web_style_sheet);

}


function initCourse(data){
     $("title").text(data.course_subject+" "+data.course_number+" --- "+data.course_semester+" "+data.course_year);
     $("#banner").text(data.course_subject+" "+data.course_number+" --- "+data.course_semester+"   "+data.course_year+"--- "+data.course_title);
     var metaTag=document.getElementsByTagName("meta");
    // metaTag.content=data.course_subject+" "+data.course_number+" --- "+data.course_semester+"   "+data.course_year+"--- "+data.course_title;

}

function initNavBar(data){
    boolHome=data.course_home;
    boolSyllabus=data.course_syllabus;
    boolSchedule=data.course_schedule;
    boolHws=data.course_hws;
    boolProject=data.course_project;

  if (!boolHome){
      $("#home_link").hide();
  }

  if(!boolSyllabus){
      $("#syllabus_link").hide();
  }

  if(!boolSchedule){
       $("#schedule_link").hide();
  }

 if(!boolHws){
       $("#hws_link").hide();
  }
   if(!boolProject){
       $("#project_link").hide();
  }


}
