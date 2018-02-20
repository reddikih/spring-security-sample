# Spring Security Sample

## Usage

Create User
``` sh
curl -v -XGET -H 'Content-type:application/json' http://localhost:8080/register -d '{"username":"test", "password":"password", "email":"test@example.com", "orb_id":"uuid", "role":"ROLE_USER"}'
```

Resource access with credentials
``` sh
curl -v -XGET http://localhost:8080/bar -u test:password

```

Resource access with session id
``` shll
curl -v -XGET http://localhost:8080/users -b 'JSESSIONID='
```

