<#import "common/common.ftlh" as com>
<@com.main>
    <h2>${facultyName}</h2>
    <div>Change Certificate Information</div>
    <form method="post" enctype="multipart/form-data">
        <div><label>Certificate number:
                <input type="text" name="certificateNumber" value="${certificateInfo.getCertificateNumber()}">
            </label></div>
        <div><label>School name :
                <input type="text" name="schoolName" value="${certificateInfo.getSchoolName()}">
            </label></div>
        <div><label>End date of school :
                <input type="date" name="endDate" value="${certificateInfo.getEndSchoolDate()}">
            </label></div>
        <#list certificateInfo.getDisciplines() as discipline>
            <div><label>${discipline.disciplineName} :
                    <input type="number" max="200" name="scores" value="${discipline.getScore()}">
                </label></div>
        </#list>
        <div>
            <p>Change Passport Photo</p>
            <input type="file" name="certificatePhotofile" id="passport">
        </div>
        <br/>
        <button type="submit">Save</button>
    </form>
    <div>
        <img src="${certificateInfo.getCertificateFilename()}"/>
    </div>
</@com.main>