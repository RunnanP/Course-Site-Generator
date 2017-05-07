//this for project
// DATA TO LOAD
var work;
var daysOfWeek;
var redInc;
var greenInc;
var blueInc;

function Work(hSemester, hProjects) {
    this.semester = hSemester;
    this.projects = hProjects;
}
function Project(hName, hStudents, hLink) {
    this.name = hName;
    this.students = hStudents;
    this.link = hLink;
}
function initProjects() {
    var dataFile = "./data/SiteSaveTestExport.json";
    loadData(dataFile);
}
function loadData(jsonFile) {
    $.getJSON(jsonFile, function (json) {
        loadJSONData(json);
        addProjects();
       addStudent(json);
    });
}
function loadJSONData(data) {
    // LOAD Projects DATA
    work = new Array();
    if (data.work!=undefined){
    for (var i = 0; i < data.work.length; i++) {
        var workData = data.work[i];
        var wProjects = new Array();


        if (workData.projects!=undefined){
        for (var j = 0; j < workData.projects.length; j++) {
            var projectData = workData.projects[j];
            var pStudents = new Array();
            for (var k = 0; k < projectData.students.length; k++) {
                pStudents[k] = projectData.students[k];
            }
            var project = new Project(projectData.name, pStudents, projectData.link);
            wProjects[j] = project;
        }
    }
        wWork = new Work(data.work[i].semester, wProjects);
        work[i] = wWork;
    }

}
}

function addProjects() {
    var div = $("#project_tables");
    for (var i = 0; i < work.length; i++) {
        var wWork = work[i];
        var text = "<h3>" + wWork.semester + " Projects</h3>"
                + "<table><tbody>";        
        var projects = wWork.projects;
        for (var j = 0; j < projects.length; j+=4) {
            var project = projects[j];
            text += "<tr>";
            for (var k = 0; k < 4; k++) {
                text += getProjectCell(projects[j+k]);
            }
            text += "</tr>";        
        }
        text += "</tbody></table><br /><br />";
        div.append(text);
    }
}



function addStudent(json){
        var div = $("#student_tables");
        var starray=json.students;
        var text="<table><tbody>";
    for (var i = 0; i < starray.length; i++) {
        var st = starray[i];
            
        var firstn="FirstName: "+st.firstName;
        var lastn="LastName: "+st.lastName;
        var teamname="Team: "+st.team;
        var role="Role: "+st.role;

            text += "<tr>";
            text+=firstn+ "<br />"+lastn+"<br />"+teamname+"<br />"+role+"<br />";
            text += "</tr>"; 
            text+=   "<br />";    
        }
        text += "</tbody></table><br /><br />";
        div.append(text);
    }

function getProjectCell(project) {
    if (project!=undefined){
    var text = "<td style='padding-right:100px'><a href=\""
            + project.link
            + "\"><img width='100' height='100' src=\"./images/projects/"
            + project.name
            + ".png\" /></a><br />"
            + "<a href=\""
            + project.link
            + "\">" + project.name + "</a><br />"
            + "<ul>";
    for (var k = 0; k < project.students.length; k++) {
        text += "<li>" + project.students[k].stu + "</li>";
    }
    text += "</ul><br /><br /><br /></td>";

    return text;}
}









//team and student

// CONSTANTS
var IMG_PATH;
var LEAD_ROLE;
var PM_ROLE;
var DESIGN_ROLE;
var DATA_ROLE;
var MOBILE_ROLE;
var NONE;
var AVAILABLE_RED;
var AVAILABLE_GREEN;
var AVAILABLE_BLUE;

// DATA TO LOAD
var teams;
var teamNames;
var availableStudents;


function initTeamsAndStudents() {
    IMG_PATH = "./images/students/";
    LEAD_ROLE = "Lead Programmer";
    PM_ROLE = "Project Manager";
    DESIGN_ROLE = "Lead Designer";
    DATA_ROLE = "Data Designer";
    MOBILE_ROLE = "Mobile Developer";
    NONE = "none";
    AVAILABLE_RED = 120;
    AVAILABLE_GREEN = 200;
    AVAILABLE_BLUE = 70;
    teams = new Array();
    teamNames = new Array();
    availableStudents = new Array();
    var dataFile = "./data/SiteSaveTestExport.json";
    loadTSData(dataFile);
}

function Team(initName, initRed, initGreen, initBlue, text_color) {
    this.name = initName;
    this.red = initRed;
    this.green = initGreen;
    this.blue = initBlue;
    this.students = new Array();
    this.text_color = text_color;
}

function Student(initLastName, initFirstName, initTeam, initRole) {
    this.lastName = initLastName;
    this.firstName = initFirstName;
    this.team = initTeam;
    this.role = initRole;
}



function loadTSData(jsonFile) {
    $.getJSON(jsonFile, function (json) {
	loadTeams(json);
	loadStudents(json);
	initPage();
    });
}

function loadTeams(data) {
    for (var i = 0; i < data.teams.length; i++) {
	var rawTeam = data.teams[i];
	var team = new Team(rawTeam.name, rawTeam.red, rawTeam.green, rawTeam.blue, rawTeam.text_color);
	teams[team.name] = team;
	teamNames[i] = team.name;
    }
}

function loadStudents(data) {
    var counter = 0;
    for (var i = 0; i < data.students.length; i++) {
	var rawStudent = data.students[i];
	var student = new Student(rawStudent.lastName, rawStudent.firstName, rawStudent.team, rawStudent.role);
	if (student.team == NONE)
	    availableStudents[counter++] = student;
	else {
	    var team = teams[student.team];
        if(team!=undefined){
            console.log("1");
	    team.students[student.role] = student;}
	}
    }
}

function initPage() {
    // FIRST ADD THE TEAM TABLES
    for (var i = 0; i < teamNames.length; i++) {
	var team = teams[teamNames[i]];
	var teamText =
		"<table style='background:rgb("
		+ team.red + ","
		+ team.green + ","
		+ team.blue + ")'>\n"
		+ "<tr>\n"
		+ "<td colspan='4' style='color:" + team.text_color + "'><strong>" + team.name + "</strong><br /></td>\n"
		+ "</tr>\n"
		+ "<tr>\n";
	teamText += addStudentToTeam(team.students[LEAD_ROLE], team.text_color);
	teamText += addStudentToTeam(team.students[PM_ROLE], team.text_color);
	teamText += addStudentToTeam(team.students[DESIGN_ROLE], team.text_color);
	teamText += addStudentToTeam(team.students[DATA_ROLE], team.text_color);
        if (team.students[MOBILE_ROLE]) {
            teamText += addStudentToTeam(team.students[MOBILE_ROLE], team.text_color);
        }
	teamText += "</tr>\n"
		+ "</table><br />\n";
	$("#teams_tables").append(teamText);
    }

    // THEN ADD THE REMAINING AVAILABLE STUDENTS
/*    var teamText =
	    "<table style='background:rgb("
	    + AVAILABLE_RED + ","
	    + AVAILABLE_GREEN + ","
	    + AVAILABLE_BLUE + ")'>\n"
		+ "<tr>\n"
		+ "<td colspan='4' style='font-size:16pt'><strong>No Team Yet</strong><br /><br /></td>\n"
		+ "</tr>\n"
		+ "<tr>\n";
    for (var i = 0; i < availableStudents.length; ) {
	teamText += "<tr>\n";
	for (var j = 0; j < 4; j++) {
	    if (i < availableStudents.length) {
		teamText += addStudentToTeam(availableStudents[i]);
		i++;
	    }
	}
	teamText += "</tr>\n";
    }
    teamText += "</table><br />\n";
    $("#teams_tables").append(teamText);
    */
}

function addStudentToTeam(student, text_color) {
	if(student!=undefined){
    var text = "<td style='color:" + text_color + "'>\n"
	    + "<img src='" + IMG_PATH + cleanText(student.lastName)
	    + cleanText(student.firstName) + ".JPG' "
	    + "alt='" + student.firstName + " " + student.lastName + "' />\n"
	    + "<br clear='all' /><strong>" + student.firstName + " " + student.lastName;
    if (student.role != NONE) {
	text +=	"<br />" + student.role; 
    }
    text += "<br /><br /></strong></td>\n";
    return text;
}
}

function cleanText(text) {
    return text.replace("-", "").replace(" ", "");
}
