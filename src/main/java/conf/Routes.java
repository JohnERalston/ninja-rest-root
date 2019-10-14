/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conf;

import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.ApplicationController;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {  
        
        router.OPTIONS().route("/.*").with(ApplicationController.class, "cors"); //Preflight end point
        
        router.GET().route("/listblogs").with(ApplicationController.class, "listBlogs");
        router.GET().route("/viewblog/{id}").with(ApplicationController.class, "viewBlog");
        router.POST().route("/createblog").with(ApplicationController.class, "createBlog");
        router.POST().route("/editblog").with(ApplicationController.class, "editBlog");
        router.POST().route("/deleteblog").with(ApplicationController.class, "deleteBlog");
    }

}

