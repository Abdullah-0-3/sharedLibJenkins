def call(Map config = [:]) {
    def recipients = config.recipients ?: error("Recipients not provided")
    def attachments = config.attachments ?: ''
    def stageName = config.stageName ?: 'Unknown Stage'

    // Automatically get project name from Jenkins environment
    def projectName = env.JOB_NAME ?: 'Unnamed Project'
    def buildNumber = env.BUILD_NUMBER ?: 'N/A'
    def buildUrl = env.BUILD_URL ?: '#'

    def subject = "[${projectName}] - Stage: ${stageName}"
    def body = """
    <html>
    <body style="background-color: #e0f7ff; font-family: Arial, sans-serif; padding: 20px;">
        <h2 style="color: #007acc;">Jenkins Pipeline Notification</h2>
        <p><strong>Project:</strong> ${projectName}</p>
        <p><strong>Build Number:</strong> #${buildNumber}</p>
        <p><strong>Latest Stage:</strong> ${stageName}</p>
        <p style="margin-top: 20px;">This is to inform you that the Jenkins pipeline is <strong>configured</strong> and running.</p>
        <p>🔗 <a href="${buildUrl}" style="color: #007acc;">View Build in Jenkins</a></p>

        <hr style="margin-top: 30px; border: none; border-top: 1px solid #b3e0ff;">
        <p style="font-size: 12px; color: #777;">Automated message from Jenkins Shared Library • Stay productive ✨</p>
    </body>
    </html>
    """

    emailext(
        to: recipients,
        subject: subject,
        body: body,
        mimeType: 'text/html',
        attachmentsPattern: attachments
    )
}
