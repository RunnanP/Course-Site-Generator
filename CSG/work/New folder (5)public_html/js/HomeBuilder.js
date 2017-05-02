


//homepage
function buildHome(){
    var dataFile = "./data/SiteSaveTestExport.json";
    loadHomeData(dataFile,newHome);
}


function loadHomeData(jsonFile,callback){
    $.getJSON(jsonFile, function(json){
        callback(json);
    });
}


function newHome(json){
     if (json.course_subject!=undefined){
      $("#course_subject").text(json.course_subject);
   
    }else{
         $("#course_subject").text("null");
    }



     if (json.course_number!=undefined){
      $("#course_number").text(json.course_number);
   
    }else{
         $("#course_number").text("null");
    }





     if (json.course_semester!=undefined){
      $("#course_semester").text(json.course_semester);
   
    }else{
         $("#course_semester").text("null");
    }





     if (json.course_year!=undefined){
      $("#course_year").text(json.course_year);
   
    }else{
         $("#course_year").text("null");
    }





     if (json.course_title!=undefined){
      $("#course_title").text(json.course_title);
   
    }else{
         $("#course_title").text("null");
    }





     if (json.course_instructor_namet!=undefined){
      $("#course_insname").text(json.course_instructor_name);
   
    }else{
         $("#course_insname").text("null");
    }




     if (json.course_instructor_hom!=undefined){
      $("#course_inshome").text(json.course_instructor_home);
   
    }else{
         $("#course_inshome").text("null");
    }



}











