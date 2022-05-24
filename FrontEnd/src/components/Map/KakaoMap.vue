<template>
  <div>
    <div id="map" ref="map"></div>
    <div class="map_wrap" style="z-index: 3">
      <div
        id="map"
        style="width: 100%; height: 100%; position: relative; overflow: hidden"
      ></div>
      <ul id="category">
        <li id="BK9" data-order="0">
          <span class="category_bg bank"></span>
          은행
        </li>
        <li id="MT1" data-order="1">
          <span class="category_bg mart"></span>
          마트
        </li>
        <li id="PM9" data-order="2">
          <span class="category_bg pharmacy"></span>
          약국
        </li>
        <li id="OL7" data-order="3">
          <span class="category_bg oil"></span>
          주유소
        </li>
        <li id="CE7" data-order="4">
          <span class="category_bg cafe"></span>
          카페
        </li>
        <li id="CS2" data-order="5">
          <span class="category_bg store"></span>
          편의점
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { BUS } from "@/store/modules/EventBus";

const houseStore = "houseStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      bounds: {
        qa: null,
        pa: null,
        ha: null,
        oa: null,
      },
      markers: [],
      cmarkers: [],
      infowindow: null,
      place: null,
      currCategory: "", // 현재 선택된 카테고리를 가지고 있을 변수입니다
      placeOverlay: null,
      contentNode: null,
      center: "",
      marker: null,
      position: {
        lat: "",
        lng: "",
      },
      itemMarker: "",
    };
  },
  computed: {
    ...mapState(houseStore, ["house", "markerPositions"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  created() {
    BUS.$on("change-hposition", (position) => {
      this.position = position;
      this.changePosition(position);
    });
  },
  mounted() {
    this.initMap();
    this.getBounds();

    BUS.$on(
      "moveDong",
      function (dong) {
        console.log("moveDong", this.place);
        this.place.keywordSearch(dong, this.dongSearchCB);
      }.bind(this)
    );
  },
  methods: {
    ...mapActions(houseStore, ["addressHouse"]),
    changePosition(position) {
      // 지도 중심 이동
      const adjLng = parseFloat(position.lng) - 0.005; // 센터 경도 조정
      this.center = new kakao.maps.LatLng(position.lat, adjLng);
      this.marker = new kakao.maps.LatLng(position.lat, position.lng);
      this.map.panTo(this.center);

      const base = this;
      // 마커 변경
      this.itemMarker.setMap(null); // 기존의 설정된 마커 제거

      var imageSrc = "https://ifh.cc/g/rxQT1B.png", // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      this.itemMarker = new kakao.maps.Marker({
        map: base.map,
        position: base.marker,
        image: markerImage, // 마커이미지 설정
      });
      this.itemMarker.setMap(this.map); // 현재 클릭한 아이템의 위치로 마커 등록
    },
    getBounds() {
      kakao.maps.load(() => {
        // 지도 영역정보를 얻어옵니다
        const bounds = this.map.getBounds();

        this.bounds.qa = bounds.qa;
        this.bounds.pa = bounds.pa;
        this.bounds.ha = bounds.ha;
        this.bounds.oa = bounds.oa;

        console.log("3.getBounds----------------------", this.bounds);
        this.addressHouse(this.bounds);
        this.displayMarker(this.markerPositions);
      });
    },
    mountMap() {
      console.log("1.mountMap------------------------");
      this.initMap();
      this.getBounds();
    },
    initMap() {
      console.log("2.initMap--------------------------");
      kakao.maps.load(() => {
        const container = document.getElementById("map");
        // const adjLng = -0.018 + parseFloat(this.position.lng);
        const options = {
          center: new kakao.maps.LatLng(37.5283169, 126.9294254),
          level: 3,
        };

        /* 카테고리 */
        //지도 객체를 등록합니다.
        //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
        this.map = new kakao.maps.Map(container, options);

        const marker = new kakao.maps.LatLng(
          this.position.lat,
          this.position.lng
        );

        this.itemMarker = new kakao.maps.Marker({
          position: marker,
        });
        this.itemMarker.setMap(this.map);

        // 장소 검색 객체를 생성합니다
        const ps = new kakao.maps.services.Places();
        this.place = ps;

        const base = this;

        // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
        const placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
        this.placeOverlay = placeOverlay;

        const contentNode = document.createElement("div"); // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
        this.contentNode = contentNode;

        // 지도에 idle 이벤트를 등록합니다
        kakao.maps.event.addListener(this.map, "idle", function () {
          base.searchPlaces();
          base.getBounds();
        });
        // kakao.maps.event.addListener(this.map, "dragend", function () {
        //   base.getBounds();
        // });
        // kakao.maps.event.addListener(this.map, "zoom_changed", function () {
        //   base.getBounds();
        // });

        // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
        this.contentNode.className = "placeinfo_wrap";

        // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
        // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
        this.addEventHandle(
          this.contentNode,
          "mousedown",
          kakao.maps.event.preventMap
        );
        this.addEventHandle(
          this.contentNode,
          "touchstart",
          kakao.maps.event.preventMap
        );

        // 커스텀 오버레이 컨텐츠를 설정합니다
        this.placeOverlay.setContent(this.contentNode);

        // 각 카테고리에 클릭 이벤트를 등록합니다
        this.addCategoryClickEvent();
      });
    },
    // 카테고리 시작
    // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },
    // 카테고리 검색을 요청하는 함수입니다
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      const bounds = this.map.getBounds();
      this.place.categorySearch(this.currCategory, this.placesSearchCB, {
        bounds: bounds,
      });
    },
    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status) {
      console.log("placesSearchCB", data);
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },
    // 지도에 마커를 표출하는 함수입니다
    displayPlaces(places) {
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      var order = document
        .getElementById(this.currCategory)
        .getAttribute("data-order");

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = this.addMarker(
          new kakao.maps.LatLng(places[i].y, places[i].x),
          order
        );

        const base = this;

        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        (function (marker, place) {
          kakao.maps.event.addListener(marker, "click", function () {
            base.displayPlaceInfo(place);
          });
        })(marker, places[i]);
      }
    },

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, order) {
      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png"; // 마커 이미지 url, 스프라이트 이미지를 씁니다
      const imageSize = new kakao.maps.Size(27, 28); // 마커 이미지의 크기
      const imgOptions = {
        spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
        spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      };
      const markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imgOptions
      );
      const marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage,
      });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.cmarkers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < this.cmarkers.length; i++) {
        this.cmarkers[i].setMap(null);
      }
      this.cmarkers = [];
    },

    // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
    displayPlaceInfo(place) {
      var content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        place.place_url +
        '" target="_blank" title="' +
        place.place_name +
        '">' +
        place.place_name +
        "</a>";

      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content +=
          '    <span title="' +
          place.address_name +
          '">' +
          place.address_name +
          "</span>";
      }

      content +=
        '    <span class="tel">' +
        place.phone +
        "</span>" +
        "</div>" +
        '<div class="after"></div>';

      this.contentNode.innerHTML = content;
      this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.map);
    },

    // 각 카테고리에 클릭 이벤트를 등록합니다
    addCategoryClickEvent() {
      console.log("addCategoryClickEvent");
      const category = document.getElementById("category");
      const children = category.children;

      for (var i = 0; i < children.length; i++) {
        const el = children[i];

        children[i].onclick = () => {
          this.onClickCategory(el);
        };
        console.log("이벤트 등록 완료");
      }
    },

    // 카테고리를 클릭했을 때 호출되는 함수입니다
    onClickCategory(element) {
      const id = element.id;
      const className = element.className;

      this.placeOverlay.setMap(null);
      if (className === "on") {
        this.currCategory = "";
        this.changeCategoryClass();
        this.removeMarker();
      } else {
        this.currCategory = id;
        this.changeCategoryClass(element);
        this.searchPlaces();
      }
    },

    // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
    changeCategoryClass(el) {
      console.log("el", el);
      var category = document.getElementById("category"),
        children = category.children,
        i;

      for (i = 0; i < children.length; i++) {
        children[i].className = "";
      }

      if (el) {
        el.className = "on";
      }
    },
    //카테고리 끝

    dongSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        const bounds = new kakao.maps.LatLngBounds();

        for (var i = 0; i < data.length; i++) {
          bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        this.map.setBounds(bounds);
        this.getBounds();
      }
    },
    displayMarker(markerPositions) {
      kakao.maps.load(() => {
        console.log("displayMarker-----------------", markerPositions);

        const positions = markerPositions.map(
          (position) => new kakao.maps.LatLng(...position)
        );

        console.log("markers : ", this.markers);
        console.log("positions : ", positions);

        const imageSrc = "https://ifh.cc/g/bNWdz3.png";
        // var imageSrc =
        //   "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png";
        const imageSize = new kakao.maps.Size(50, 55); // 마커 이미지의 크기
        const imgOptions = {
          offset: new kakao.maps.Point(27, 69),
        };
        const markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        );

        if (positions.length > 0) {
          this.markers = positions.map((position) => {
            // if (
            //   this.marker == null ||
            //   (this.marker != null &&
            //     !(
            //       position.lat == this.marker.lat &&
            //       position.lng == this.marker.lng
            //     ))
            // ) {
            new kakao.maps.Marker({
              map: this.map,
              position: position,
              image: markerImage,
            });
            // }
          });
        }
      });
    },
    // displayInfoWindow() {
    //   if (this.infowindow && this.infowindow.getMap()) {
    //     //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
    //     this.map.setCenter(this.infowindow.getPosition());
    //     return;
    //   }

    //   var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    //     iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
    //     iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    //   this.infowindow = new kakao.maps.InfoWindow({
    //     map: this.map, // 인포윈도우가 표시될 지도
    //     position: iwPosition,
    //     content: iwContent,
    //     removable: iwRemoveable,
    //   });

    //   this.map.setCenter(iwPosition);
    // },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#map {
  width: 100vw;
  height: 100vh;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: absolute;
  height: 350px;
  top: 181px;
  right: 80px;
}
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
