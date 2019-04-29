学习案例demo 欢迎一起学习
ngnix.conf  配置文件如下：
http {

	upstream app_server {
		server 127.0.0.1:8080;
		server 127.0.0.1:8081;
	}

    server {
        listen       8079;
        location / {
		      proxy_redirect off;
			  proxy_set_header Host $host;
			  proxy_set_header X-Real-IP $remote_addr;
              proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
		      proxy_pass http://app_server;
        }
    }
 }
 
 
 
 -------------------------------testfork join ----------------------------------------------
 这个工程主要是写的fork join 使用例子。 建议在spring环境上测试
