<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tailwind CSS Form</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
  <!-- Navbar -->

<%@include file="navbar.jsp" %>

<div class="flex justify-center mt-8">
  <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md hover:border-2 border-gray-300">
    <h2 class="text-2xl font-bold mb-3 text-gray-800 text-center">Login Form</h2>
           <c:if test="${not empty error}" >
                    <p class="text-center text-s text-red-800 " >${error}</p>
                </c:if>
                <c:if test="${not empty success}" >
                                    <p class="text-center text-s text-green-800 " >${success}</p>
                                </c:if>
  <form action="/login" method="post">
      <!-- Email -->
      <div class="mb-4">
        <label for="userName" class="block text-sm font-medium text-gray-700">Username</label>
        <input type="text" id="userName" name="userName" placeholder="Enter your username"
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
        <p class="inline">Not registered yet. </p><a href="/register" class="text-center text-blue-500 font-semibold"> click Here</a>
      </div>
    </form>
  </div>
</div>
</body>
</html>
