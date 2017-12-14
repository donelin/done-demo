function (key, values) {
    var result = {
        trainningClassId:"",
        notLearnYet:0,
        learning:0,
        learned:0,
        notExamYet:0,
        exammed:0,
        qualified:0
    };   
    result.trainningClassId=key;
    values.forEach(function(val) {  
         result.notLearnYet+=val.notLearnYet;
         result.learning+=val.learning;
         result.learned+=val.learned;
         result.notExamYet+=val.notExamYet;
         result.exammed+=val.exammed;
         result.qualified+=val.qualified;
    })
    return result;
};