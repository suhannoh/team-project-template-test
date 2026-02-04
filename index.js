  // localStorage 저장
  const images = [
    "https://fullstack-suhan.s3.ap-northeast-2.amazonaws.com/bag_images/%EB%B2%A0%EC%9D%B4%EC%A7%81%EC%97%90%EC%BD%94%EB%B0%B1.png",
    "https://fullstack-suhan.s3.ap-northeast-2.amazonaws.com/clothes_images/%EC%97%B0%EB%B2%A0%EC%9D%B4%EC%A7%80%EB%8B%88%ED%8A%B8%EA%B0%80%EB%94%94%EA%B1%B4.png"
  ];
  localStorage.setItem("images", JSON.stringify(images));

  // localStorage 불러오기
  const storedImages = JSON.parse(localStorage.getItem("images"));
  
  // body에 이미지 추가
  storedImages.forEach(url => {
    const img = document.createElement("img");
    img.src = url;
    img.alt = "Stored Image";
    document.body.appendChild(img);
  });