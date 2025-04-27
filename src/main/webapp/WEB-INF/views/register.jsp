<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tailwind CSS Form</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
        <!-- Navbar  -->
     <%@include file="navbar.jsp" %>
<div class="flex justify-center mt-1">

  <div class="bg-white p-1 mb-1 px-6 rounded-lg shadow-lg w-full max-w-md hover:border-2 border-gray-300">
    <h2 class="text-xl font-bold text-gray-800 text-center">Registration Form</h2>

    <c:if test="${not empty error}" >
             <p class="text-center text-s text-red-800 " >${error}</p>
         </c:if>
    <form action="/register" method="post">
      <!-- Name -->
      <div class="mb-4">
        <label for="name" class="block text-sm font-medium text-gray-700">Name</label>
        <input type="text" id="name" name="name" placeholder="Enter your name"
               class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
               required>
      </div>
      <!-- userName -->
            <div class="mb-4">
              <label for="userName" class="block text-sm font-medium text-gray-700">Username</label>
              <input type="text" id="userName" name="userName" placeholder="create your Username"
                     class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                     required>
            </div>
             <!-- Designation -->
                  <div class="mb-4">
                    <label for="designation" class="block text-sm font-medium text-gray-700">Designation</label>
                    <input type="text" id="designation" name="designation" placeholder="Enter your designation"
                           class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                           required>
                  </div>
            <!-- Salary -->
                  <div class="mb-4">
                    <label for="salary" class="block text-sm font-medium text-gray-700">Salary</label>
                    <input type="text" id="salary" name="salary" placeholder="Enter your salary"
                           class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                           required>
                  </div>
                  <!-- DOB -->
                        <div class="mb-4">
                          <label for="dob" class="block text-sm font-medium text-gray-700">Date of Birth</label>
                          <input type="date" id="dob" name="dob" placeholder="Enter your dob"
                                 class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                                 required>
                        </div>

      <!-- Contact -->
      <div class="mb-4">
        <label for="contact" class="block text-sm font-medium text-gray-700">Contact</label>
        <input type="text" id="contact" name="contact" placeholder="Enter your contact"
               class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
               required>
      </div>

       <!-- Password -->
            <div class="mb-4">
              <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
              <input type="password" id="password" name="password" placeholder="Create your password"
                     class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                     required>
            </div>

      <!-- Submit Button -->
      <button type="submit"
              class="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2">
        Submit
      </button>
            <div class="m-2 flex justify-center ">
              <p class="inline">Already registered. </p><a href="/login" class="text-center text-blue-500 font-semibold"> click Here</a>
            </div>
    </form>
  </div>

</body>
</html>
