// Email Notification

def call(String to, List<String> attachments = []) {
    def status = currentBuild.result ?: 'SUCCESS' 
    def color = (status == 'SUCCESS') ? '#28a745' : '#dc3545'
    def icon = (status == 'SUCCESS') ? '✅' : '❌'
    def buttonColor = (status == 'SUCCESS') ? '#28a745' : '#dc3545'
    def subjectStatus = (status == 'SUCCESS') ? 'SUCCESS' : 'FAILURE'
    def projectName = env.JOB_BASE_NAME 

    // Convert the list to a comma-separated string for Jenkins email plugin
    def attachmentsPattern = attachments.join(',')

    def emailBody = """
    <!DOCTYPE html>
    <html>
    <head>
        <style>
            body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
            .container { max-width: 600px; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }
            .header { background: ${color}; color: white; padding: 15px; font-size: 18px; font-weight: bold; text-align: center; border-radius: 5px 5px 0 0; }
            .content { padding: 20px; color: #333; line-height: 1.6; }
            .footer { text-align: center; font-size: 12px; color: #777; margin-top: 20px; }
            .button { display: inline-block; padding: 10px 20px; margin-top: 20px; background: ${buttonColor}; color: white; text-decoration: none; border-radius: 5px; font-weight: bold; }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">${icon} Jenkins Pipeline Execution - ${subjectStatus}</div>
            <div class="content">
                <p><strong>Project Name:</strong> ${projectName}</p>
                <p>The Jenkins pipeline for <strong>${projectName}</strong> has ${status == 'SUCCESS' ? 'been successfully executed.' : 'encountered a failure. Please review the logs and investigate the issue.'}</p>
                <p>You can check the build details by clicking the button below:</p>
                <a href="${env.BUILD_URL}" class="button">View Pipeline</a>
            </div>
            <div class="footer">© 2025 DevOps Shack | Automated Notification</div>
        </div>
    </body>
    </html>
    """

    emailext(
        subject: "${icon} ${subjectStatus} - Jenkins Pipeline: ${projectName}",
        body: emailBody,
        attachmentsPattern: attachmentsPattern,
        mimeType: 'text/html',
        to: to
    )
}
