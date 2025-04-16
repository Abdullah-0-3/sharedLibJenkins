def call(Map config = [:]) {
    def recipients = config.recipients ?: error("Recipients not provided")
    def attachmentsInput = config.attachments ?: ''
    def stageName = config.stageName ?: 'Unknown Stage'

    def projectName = env.JOB_NAME ?: 'Unnamed Project'
    def buildNumber = env.BUILD_NUMBER ?: 'N/A'
    def buildUrl = env.BUILD_URL ?: '#'

    // 🛠 Convert list of attachments to a comma-separated string, 
    // ensuring the correct path for attachments
    def attachmentsPattern = attachmentsInput instanceof List 
        ? attachmentsInput.collect { "path/to/files/${it}" }.join(',')
        : attachmentsInput

    // Debugging: Log the attachments pattern
    echo "Attachments Pattern: ${attachmentsPattern}"

    def subject = "[${projectName}] - Stage: ${stageName}"
    def body = """
    <html>
    <head>
        <style>
            body {
                background-color: #f7f9fc; /* Light background color */
                font-family: 'Poppins', sans-serif; /* Using Poppins font */
                padding: 20px;
            }
            h2 {
                color: #007acc; /* Sky blue for headers */
            }
            p {
                color: #333; /* Dark text for better readability */
            }
            a {
                color: #007acc; /* Sky blue for links */
                text-decoration: none; /* Remove underline from links */
            }
            a:hover {
                text-decoration: underline; /* Underline links on hover */
            }
            hr {
                margin-top: 30px; 
                border: none; 
                border-top: 1px solid #b3e0ff; /* Light sky blue border */
            }
            .footer {
                font-size: 12px; 
                color: #777; 
            }
        </style>
    </head>
    <body>
        <h2>Jenkins Pipeline Notification</h2>
        <p><strong>Project:</strong> ${projectName}</p>
        <p><strong>Build Number:</strong> #${buildNumber}</p>
        <p><strong>Latest Stage:</strong> ${stageName}</p>
        <p style="margin-top: 20px;">This is to inform you that the Jenkins pipeline is <strong>configured</strong> and running.</p>
        <p>🔗 <a href="${buildUrl}">View Build in Jenkins</a></p>

        <hr>
        <p class="footer">Automated message from Jenkins Shared Library • Stay productive ✨</p>
    </body>
    </html>
    """

    emailext(
        to: recipients,
        subject: subject,
        body: body,
        mimeType: 'text/html',
        attachmentsPattern: attachmentsPattern
    )
}