function () {
  
	var yesterDay = new Date();
	yesterDay.setDate(yesterDay.getDate()-1);
	yesterDay.setHours(23);
	yesterDay.setMinutes(59);
	yesterDay.setSeconds(59);
    this.classStudyDetails.forEach(function(classStudent){
    if(classStudent.isTransfer==1){//过滤掉迁移数据
	}else{
    	if(	(
    			 (classStudent.openTime!=undefined&&classStudent.openTime.getTime()<=yesterDay.getTime())
    			 &&(classStudent.swapOutTime==undefined||classStudent.swapOutTime.getTime()>yesterDay.getTime())
    		)||
    		(
    	    	 (classStudent.swapInTime!=undefined&&classStudent.swapInTime.getTime()<=yesterDay.getTime())
    	    	 &&(classStudent.swapOutTime==undefined||classStudent.swapOutTime.getTime()>yesterDay.getTime())
    	    )
	     ){
    		
    			   if(trainClassIds.length>0&&(trainClassIds.indexOf(classStudent.trainClassId)<=-1 )){
    		        }else{
    		            if(learnTimeYear!=""&&(learnTimeYear!=classStudent.trainingYearId)){
    		            }else{
    		                 if(learnCategory!=""&&(learnCategory!=classStudent.studyTypeId)){
    		                 }else{
    		                      if(professionalLevel!=""&&(professionalLevel!=classStudent.professionalGradeId)){
    		                      }else{
    		                          var result = {
    		                            trainningClassId:"",
    		                            notLearnYet:0,
    		                            learning:0,
    		                            learned:0,
    		                            notExamYet:0,
    		                            exammed:0,
    		                            qualified:0
    		                            };
    		                        result.trainningClassId=classStudent.trainClassId;
    		                        if(classStudent.studyStatus==1){
    		                           result.notLearnYet=1;
    		                        }else if(classStudent.studyStatus==2){
    		                            result.learning=1;
    		                        }else if(classStudent.studyStatus==3){
    		                            result.learned=1;
    		                        }
    		                        if(classStudent.examineStatus==1){
    		                            result.notExamYet=1;
    		                        }else if(classStudent.examineStatus==2){
    		                            result.exammed=1;
    		                        }
    		                        if(classStudent.qualified==1){
    		                            result.qualified=1;
    		                        }
    		                        emit(classStudent.trainClassId, result); 
    		                      }
    		                     
    		                 }
    		            }
    		        }
    		}
	}
    });
};