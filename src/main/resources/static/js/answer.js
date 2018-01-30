$(".submit-write input[type='submit']").on("click", addAnswer);

function addAnswer(e) {
    e.preventDefault();

    var queryString = $(".submit-write").serialize();
    var url = $(".submit-write").attr("action");

    $.ajax({
        type : 'post',
        url : url,
        data : queryString,
        dataType : 'json',
        error: function (e) {
            alert(e);
        },
        success : function (data, status) {
            console.log(data)
            var answerTemplate = $("#answerTemplate").html();
            var template = answerTemplate.format(data.writer.name, getDateString(data.date), data.contents, data.question.questionId, data.answerId);
            $(".qna-comment-list").append(template);
            $("textarea[name=contents]").val("");
        }
    });
}

function getDateString(timestamp){
    const date = new Date(timestamp);
    let month = date.getMonth() + 1;
    if (month < 10) month = "0" + month;
    return date.getFullYear()
        + "-" + month
        + "-" + date.getDate()
        + " " + date.toTimeString().split(" ")[0]
        + "." + date.getMilliseconds();
}

$(".qna-comment-slipp-articles").on("click", ".delete-answer-form button[type='submit']", deleteAnswer);

function deleteAnswer(e){
    e.preventDefault();

    var deleteBtn = $(this);
    var url = deleteBtn.attr("request");
    $.ajax({
        type : 'delete',
        url : url,
        dataType : 'json',
        error : function (xhr, status) {
        },
        success : function (data, status) {
            if (status==="success") {
                deleteBtn.closest("article").remove();
                } else {
                alert(data.errorMessage);
            }
        }
    });
}
