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
        
    });

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
