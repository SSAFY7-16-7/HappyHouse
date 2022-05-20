package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HappyHouseException;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.InterestApt;
import com.ssafy.happyhouse.model.dto.LikeCount;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.userService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Api(tags = {"회원 컨트롤러"})
public class UserController {
	
	@Autowired
	private JwtServiceImpl jwtService;

	
	@Autowired
	private userService service;
	
	private static final Logger logger  = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS ="success";
	private static final String FAIL = "fail";
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e){
		logger.info("ErrorHandler.......................................");
		logger.info("ErrorMessage.......................................{}",e.getMessage());
		e.printStackTrace();
		
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		if( e instanceof HappyHouseException) {
			return new ResponseEntity<String>(e.getMessage(), resHeaders,HttpStatus.FAILED_DEPENDENCY);
		}else {
			return new ResponseEntity<String>("처리 중 오류 발생", resHeaders, HttpStatus.FAILED_DEPENDENCY);
		}
	}

	// 회원 목록 get /user
	@ApiOperation(value="회원 목록 조회", notes = "회원목록을 조회하기")
	@GetMapping("/user")
	public ResponseEntity<?> userList() {
		try {
			List<User> userList = service.listUser();
				return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error(" + this.getClass().getName() + ") "
					+ "("+Thread.currentThread().getStackTrace()[1].getMethodName() + "):" + e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}

	// 회원 검색 get /user/${id}
	@ApiOperation(value="회원 1명 조회", notes = "id가 일치하는 회원 데이터 1개 조회")
	@GetMapping("/user/{userid}")
	public ResponseEntity<?> userInfo(@PathVariable("userid") String id) {
		try {
			User find = service.infoUser(id);
			return new ResponseEntity<User>(find, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error(" + this.getClass().getName() + ") "
					+ "("+Thread.currentThread().getStackTrace()[1].getMethodName() + "):" + e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}
	
	@ApiOperation(value="회원 1명 조회", notes = "id가 일치하는 회원 데이터 1개 조회")
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody Map<String, String> info) {
		try {
			List<User> list = service.findByKey(info.get("key"), info.get("word"));
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error(" + this.getClass().getName() + ") "
					+ "("+Thread.currentThread().getStackTrace()[1].getMethodName() + "):" + e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}

	// 회원 가입 post /user
	@ApiOperation(value="회원 정보 등록", notes = "User 객체를 입력받아서 등록함(회원가입)")
	@PostMapping("/regist")
	public ResponseEntity<?> userRegist(@RequestBody User user) {
		try {
			service.registerUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error(" + this.getClass().getName() + ") "
					+ "("+Thread.currentThread().getStackTrace()[1].getMethodName() + "):" + e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}

	// 회원 정보 수정 put /user
	@ApiOperation(value="회원 정보 수정", notes = "회원정보를 수정함")
	@PutMapping("/update")
	public ResponseEntity<?> userUpdate(@RequestBody User user,HttpServletRequest request) {
		try {
			service.updateUser(user);
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", user);
			System.out.println("회원정보 수정  - id:"+user.getId());
			return new ResponseEntity<User>(user, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println("Error(" + this.getClass().getName() + ") "
					+ "("+Thread.currentThread().getStackTrace()[1].getMethodName() + "):" + e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}

	// 회원 삭제 delete /user
	@ApiOperation(value="회원 삭제", notes = "id가 일치하는 회원 데이터 1개 삭제")
	@PostMapping("/remove")
	public ResponseEntity<?> userRemove(@RequestBody String id,HttpServletRequest request) {
		try {
			System.out.println(id);
			service.deleteUser(id);
			HttpSession session = request.getSession();
			session.invalidate();
			return new ResponseEntity<Void>( HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error(" + this.getClass().getName() + ") "
					+ "("+Thread.currentThread().getStackTrace()[1].getMethodName() + "):" + e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}

	// 로그인 post/login
	@ApiOperation(value="회원 1명 조회", notes = "id가 일치하는 회원 데이터 1개 조회")
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody Map<String, String> info) {
		System.out.println(info.get("id")+" "+info.get("password"));
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = service.login(info.get("id"), info.get("password"));
			String token = jwtService.create("userid", loginUser.getId(), "access-token");
			logger.debug("로그인 토큰정보 : {}", token);
			System.out.println(token);
			resultMap.put("access-token", token);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}finally {
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
	}
	
	@ApiOperation(value="회원 1명 조회", notes = "id가 일치하는 회원 데이터 1개 조회")
	@GetMapping("/likeList/{userid}")
	public ResponseEntity<?> likeList(@PathVariable String userid) {
		try {
			List<HouseInfo> list = service.likeList(userid);
				return new ResponseEntity<List<HouseInfo>>(list, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error(" + this.getClass().getName() + ") "
					+ "("+Thread.currentThread().getStackTrace()[1].getMethodName() + "):" + e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}
	
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User loginUser = service.infoUser(userid);
				resultMap.put("userInfo", loginUser);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@ApiOperation(value="관심아파트 설정", notes = "관심 아파트테이블에 해당 id와 aptCode 저장")
	@PostMapping("/likeList")
	public ResponseEntity<?> addLike(@RequestBody InterestApt info){
		try {
			service.addLike(info);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(Exception e) {
			System.out.println("Error(" + this.getClass().getName() + ") "
					+ "("+Thread.currentThread().getStackTrace()[1].getMethodName() + "):" + e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}
	
	@ApiOperation(value="관심아파트 삭제", notes = "관심 아파트테이블에 해당 id와 aptCode가 맞는 데이터 삭제")
	@DeleteMapping("/likeList/{aptcode}")
	public ResponseEntity<?> removeLike(@PathVariable String aptcode, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("userInfo");
			InterestApt info = new InterestApt(user.getId(),Integer.parseInt(aptcode));
			service.removeLike(info);
			return new ResponseEntity<Void>( HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error(" + this.getClass().getName() + ") "
					+ "("+Thread.currentThread().getStackTrace()[1].getMethodName() + "):" + e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}
	
	@ApiOperation(value="관심설정된 아파트 리스트", notes = "관심설정된 아파트 정보들을 리스트로 가져오기")
	@GetMapping("/likeCntList")
	public ResponseEntity<?> likeCntList() {
		try {
			List<LikeCount> list = service.getLikeCntList();
				return new ResponseEntity<List<LikeCount>>(list, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error(" + this.getClass().getName() + ") "
					+ "("+Thread.currentThread().getStackTrace()[1].getMethodName() + "):" + e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}
	
	

}
