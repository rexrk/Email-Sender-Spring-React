import React, { useState } from "react";
import toast from "react-hot-toast";
import { sendEmail } from "../api/email.service";

function EmailSender() {
  const initialEmailData = {
    to: "",
    subject: "",
    body: "",
  };

  const [emailData, setEmailData] = useState(initialEmailData);
  const [sending, setSending] = useState(false);

  function handleChanges(event, name) {
    setEmailData({ ...emailData, [name]: event.target.value });
  }

  async function handleSubmit(event) {
    event.preventDefault();
    try {
      setSending(true)
      await sendEmail(emailData);
      toast.success("Email sent successfully");
      handleReset();
      
    } catch (error) {
      console.log(error);
      toast.error("Error Sending email");
      
    } finally {
      setSending(false)
      
    }
  }

  function handleReset() {
    setEmailData(initialEmailData);
  }

  return (
    <div className="w-full min-h-screen flex justify-center items-center">
      <div className="email_card bg-white md:w-1/3 w-full mx-10 p-5 rounded-lg shadow dark:bg-gray-700">
        {/* headings */}

        <h1 className="text-gray-800 dark:text-gray-50 text-3xl flex justify-center">Email Spring App</h1>
        <p className="text-gray-300 flex justify-center my-4">Compose New Email</p>

        {/* form */}

        <form onSubmit={handleSubmit}>
          <div className="input_field mt-4">
            {/* to */}
            <label
              htmlFor="toEmail"
              className="mb-2 font-medium text-gray-500 dark:text-white dark:text-white"
            >
              To:
            </label>
            <div className="relative">
              <input
                value={emailData.to}
                onChange={(event) => handleChanges(event, "to")}
                type="email"
                id="toEmail"
                className="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Recipient"
                required
              />
            </div>

            {/* subject */}

            <label
              htmlFor="subject"
              className="mb-2 font-medium text-gray-500 dark:text-white"
            >
              Subject:
            </label>
            <div className="relative">
              <input
                value={emailData.subject}
                onChange={(event) => handleChanges(event, "subject")}
                type="text"
                id="subject"
                className="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Enter Subject"
                required
              />
            </div>
          </div>

          {/* body box */}

          <label
            htmlFor="body"
            className="mb-2 font-medium text-gray-500 dark:text-white"
          >
            Body:
          </label>
          <textarea
            value={emailData.body}
            onChange={(event) => handleChanges(event, "body")}
            id="body"
            rows="10"
            className="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            placeholder="Write Your Message"
            required
          ></textarea>

          {/* loader */}

          {sending && (
            <div className="loader flex justify-center mt-4">
              <div role="status">
                <svg
                  aria-hidden="true"
                  class="w-8 h-8 text-gray-200 animate-spin dark:text-gray-600 fill-blue-600"
                  viewBox="0 0 100 101"
                  fill="none"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z"
                    fill="currentColor"
                  />
                  <path
                    d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z"
                    fill="currentFill"
                  />
                </svg>
                <span class="sr-only">Loading...</span>
              </div>
            </div>
          )}

          {/* buttons */}

          <div className="button_container mt-4 flex justify-center">
            <button
            disabled={sending}
              type="submit"
              className="bg-blue-600 text-white hover:bg-blue-900 px-4 py-2 rounded"
            >
              Send Email
            </button>

            <button
              type="button"
              onClick={handleReset}
              className="ml-auto bg-transparent border-none outline-none"
            >
              <svg
                className="w-6 h-6 text-gray-800 dark:text-white"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="currentColor"
                viewBox="0 0 20 16"
              >
                <path d="M19 0H1a1 1 0 0 0-1 1v2a1 1 0 0 0 1 1h18a1 1 0 0 0 1-1V1a1 1 0 0 0-1-1ZM2 6v8a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V6H2Zm11 3a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1V8a1 1 0 0 1 2 0h2a1 1 0 0 1 2 0v1Z" />
              </svg>
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default EmailSender;
