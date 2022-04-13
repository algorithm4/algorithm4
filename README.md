# 허프만 압축 [팀 과제]

# 1. text 전처리
### - 허프만 부호화 알고리즘에 넣기 위한 데이터를 전처리 하였다.
### - 압축 효율을 높이고 성능분석을 편하게 하기 위함
### - 문장부호, 공백, 숫자, 특수문자 등을 제거
## **1) 알고리즘에 넣기 위한 txt 선정**
### - 영문으로 된 문서가 편리할 것 같아 alice_in_wonderland.txt 파일로 선정하였다.
### - github에서 다운로드
## **2) 파일 읽고 텍스트를 전처리 하는 코드 작성**
### - 작성 언어는 python
### - 파일 오픈 -> clean_text -> 쓰기 모드로 파일 오픈 후 전처리 된 결과를 파일에 작성 -> 파일 닫기
### 💻 작성 코드
```python
import os
from re import findall, sub

print('\n현재 경로 :', os.getcwd())

#파일 오픈-str 형태로 받아준다
with open("/Users/kimnuri/PycharmProjects/ComputerAlgorithm/huffman/data/alice_in_wonderland.txt") as f:
    content = " ".join([l.rstrip() for l in f])

#전처리 위한 함수 수행
def clean_text(content):
    texts_re = content.lower() #소문자로 변경
    texts_re2 = sub('[0-9]', '', texts_re) #숫자 제거
    texts_re3 = sub('[\'`,.?!;:"\-\[\]]', '', texts_re2) #문장부호 제거
    texts_re4 = sub('[@#$%^&*()]', '', texts_re3) #특수문자 제거
    texts_re5 = sub('[가-힣]','',texts_re4) #한글 제거
    texts_re6 = texts_re5.replace(' ','') #white space 제거
    return texts_re6

print(clean_text(content))

#위에서 처리된 결과를 파일에 다시 써준다
texts2 = open('/Users/kimnuri/PycharmProjects/ComputerAlgorithm/huffman/data/alice_in_wonderland.txt',mode='w',encoding='UTF8')
texts2.write(clean_text(content))
texts2.close()
```
### 💻 결과 화면
![결과1](https://user-images.githubusercontent.com/101931446/162650590-8cc73f34-e246-4459-80ac-b693f4ed0fab.png)
### **주의할 점**
#### - 파일 쓰기모드로 오픈 하였기 때문에 파일을 str 구조로 불러와야 했다.
#### - 문장 부호 제거에서 파이썬 언어로 인식 되는 부분들을 신경써야 했다.
## **3) 성능 분석을 위한 알파벳 빈도수와 텍스트 길이 계산**
### - 이론적으로 이진코드를 구하기 위해 알파벳 빈도수를 계산하였다.
### - 압축률을 이론적으로 구하기 위해 text의 길이를 계산하였다.
### 💻 작성 코드
```python
import os
from re import findall, sub

#파일 오픈-str구조로 받아온다
with open("/Users/kimnuri/PycharmProjects/ComputerAlgorithm/huffman/data/alice_in_wonderland.txt") as f:
    content = " ".join([l.rstrip() for l in f])

Alphabet = 'abcdefghijklmnopqrstuvwxyz' #for문에서 돌리기 위함과 빈도수를 계산하기 위해 미리 문자열을 만들어준다

texts_freq = [0] * 26

#알파벳 빈도수 계산
for al in content:
    if al in Alphabet:
        idx = Alphabet.find(al)
        texts_freq[idx] += 1

#알파벳 빈도수 출력
print("알파벳 빈도수")
for i in range(0,26):
    print(Alphabet[i], ":", texts_freq[i])

#텍스트 길이
print('texts 길이 =',len(content))
```
### 💻 결과 화면
![결과2](https://user-images.githubusercontent.com/101931446/162650689-7638a4a1-2557-47ec-a396-91296cd06181.png)


4)압축의 필요성
------------------

먼저 압축이란 작업이 왜 필요할까? 

1. 데이터양이 많은 파일을 압축하여 저장장치에 효율적으로 저장.
2. 데이터양이 많은 파일을 압축하여 통신 네트워크로 전달.
3. 여러 데이터들을 간편하고 효율적으로 관리.

이렇게 데이터양이 많으면 , 데이터 전송 , 저장 등 데이터를 다루는데 부담이 가게 된다.
그렇기에 압축이라는 작업이 필요한것이다.


# 2. 팀 실습
------------------
나는 JAVA를 통해 허프만 코드를 구현하는 역할을 맡았다.
혼자하는데 버거움이 있어, 팀원들의 도움을 받기도 했고,
여러 깃허브 , 유튜브 , 구글에서 다른 사람들이 구현해놓은 코드를 보면서 구현해보았다.

우리 팀이 압축해 볼 텍스트 파일은  **'이상한 나라의 엘리스'**  영문판이다.

![alice](https://user-images.githubusercontent.com/101388379/162629026-3bceb619-b818-452c-904b-723f981d1ce3.PNG)

하지만 공백과 특수 문자가 많아 , 이대로는 압축효율을 내기 힘들 것이라고 판단하였다.

그래서 먼저 다른 팀원이 특수문자와 공백을 지워주는 알고리즘을 통해 원문의 공백과 특수문자를 모두 제거했다.

![alice2](https://user-images.githubusercontent.com/101388379/162629157-28fb1ac7-06fc-4228-996a-21eb4e883c86.PNG)


**먼저 주어진 텍스트에서 각 영문자의 출현 빈도수를 구한다.**
```java
public static void numberoftexts(String src) {
        // 영문자 빈도 수 조사
        try {
            // 파일 객체 생성
            File file = new File(src);
            // file에 대한 입력 스트림 생성
            FileReader filereader = new FileReader(file);
            // 입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line;
            // 파일 한줄씩 읽기
            while ((line = bufReader.readLine()) != null) {
                // 문자열의 문자 하나하나 확인
                for (int i = 0; i < line.length(); i++) {
                    char temp = line.charAt(i);
                    // 현재 문자가 이미 1번 이상 들어가 있으면 문자에 해당하는 빈도 수 1증가
                    if (txt.containsKey(temp)) {
                        txt.put(temp, txt.get(temp) + 1);
                    } else {
                        txt.put(temp, 1);
                    }
                }
            }
            // 읽기 종료
            bufReader.close(); 
 ```


**가장 작은 빈도수를 가지는 노드 2개를 합쳐 부모 노드를 만든다.**
```java
 while(true) {
            // 최소값 2개 추출
            Node leftChild = mini.MinNode();
            Node rightChild = mini.MinNode();
            // 왼쪽 노드와 오른쪽 노드를 더한 값을 넣을 부모 노드 생성
            plusparent = new Node(leftChild.txt+rightChild.txt,'.');
            plusparent.leftNode=leftChild;
            plusparent.rightNode=rightChild;
            // 힙이 비어있다면 return 
            if(mini.isEmpty()) return;
            // 새로운 부모 노드 최소힙에 삽입
            mini.insert(plusparent);
        }
```
**이 작업을 노드가 1개 남을때까지 반복한다.**

**완성한 트리의 왼쪽 간선에는 0, 오른쪽 간선에는 1**
```java
 public static void HuffmanCode(Node root, int[] code, int idx) {
        // 왼쪽 노드가 있다면 허프만 코드는 0
        if(root.leftNode != null) {
            code[idx]=0;
            // 다음 노드의 허프만 코드
            HuffmanCode(root.leftNode, code, idx+1);
        }
        // 오른쪽 노드가 있다면 허프만 코드는 1
        if(root.rightNode !=null) {
            code[idx]=1;
            HuffmanCode(root.rightNode, code, idx+1);
        }

```

이렇게 컴파일 하여 영문자들의 빈도수 그에 맞게 할당된 이진코드를 구할 수 있다.

빈도수를 측정한 결과.
![Freq](https://user-images.githubusercontent.com/101388379/162628575-0ecf3824-a20a-4a73-8564-7654e8ae0d84.PNG)




이것에 이진코드를 할당.

![binary](https://user-images.githubusercontent.com/101388379/162628647-edce3538-ec7d-47ac-941f-d78a86902451.PNG)



허프만 코드는 빈도수가 가장 높은 문자에 가장 짧은 코드를 할당하고 , 빈도수가 낮은 문자에는 긴 코드를 할당하여
압축의 효율성을 높인다

실제로 빈도수가 78로 가장 낮은 'z'는 11자리의 가장 긴 코드를 할당 받았고
![z](https://user-images.githubusercontent.com/101388379/162629692-fe452794-2c5b-4046-9d86-4792908f3924.PNG)

빈도수가 13579로 가장 높은 'e'는 3자리의 가장 짧은 코드를 할당 받았다.
![e](https://user-images.githubusercontent.com/101388379/162629754-8ae10b0d-9ac3-4716-bd05-b3a9ccf3a496.PNG)


# 3. 허프만 코드 압축률

허프만 압축의 효율성을 알아보기 위해
허프만 압축의 압축률이 얼마나 되는지 알아볼 것이다. <br>
텍스트 파일을 압축하고 , 압축률을 구하는데 
https://github.com/moomyung1013/Huffman-Coding 참조했다.

먼저 우리 팀이 압축하기로 한 '이상한 나라의 엘리스' 영문 버전이다.

![압축률 비교](https://user-images.githubusercontent.com/101388379/162631419-8d6b26d5-58f6-45e5-8064-73c5460fd008.PNG)

**107,748byte -> 65,777byte 로 파일크기가 줄었다. 대략 40% 압축이 된 셈이다.**

과연 어느정도 크기의 파일을 압축할때 , 가장 압축률이 클지 궁금하여 , 임의로 가져온 1KB , 5KB , 25KB , 50KB 텍스트 파일들을 
함께 비교해보았다.

 ![비교](https://user-images.githubusercontent.com/101388379/162632019-cd44a1d7-79a5-4dca-9674-37eb46811a1d.PNG)

**12,896byte -> 9,097byte (약 30% 압축) <br>
54,069byte -> 35,510byte (약 35% 압축) <br>
241.851byte -> 159,409byte (약 35% 압축) <br>
544,469byte -> 361,690byte (약 34% 압축)**

대략 30% ~ 40%의 압축률을 보이고 있다.

![압축률 그래프](https://user-images.githubusercontent.com/101388379/162632391-d5b0e777-76e5-4fb9-a488-941d93c35bd8.PNG)

그래프로 나타내 쉽게 보면 결국 우리가 처음에 압축했던 15KB의 '이상한 나라의 엘리스' 가 가장 높은 압축률을 보였다.
하지만 같은 크기의 파일이라도 압축률은 충분히 달라질 수 있다. 허프만 압축은 최대 80%의 압축률까지 보여준다고 한다.
어떠한 영문자가 얼마나 빈번하게 나오느냐에 따라 압축률 또한 달라 질 수 있을 것 같다.
다음에는 좀 더 발전해 , GUI까지 구성된 압축 프로그램을 만들어보는 기회를 가지고 싶다.


# 4. 허프만 코드 이진트리    

허프만 코드 이진트리는 텍스트 압축을 위해 사용하는 방법으로 사용 빈도가 높으면 작은 비트로 낮으면 큰 비트로 변환해서 나타낸 트리다.    
출현 빈도를 구했으면 허프만 코드에 입력시키고, 입력시킨 텍스트들이 변환값을 가진다.    

            Node leftChild = mini.MinNode();
            Node rightChild = mini.MinNode();

            plusparent = new Node(leftChild.txt+rightChild.txt,'.');
            plusparent.leftNode=leftChild;
            plusparent.rightNode=rightChild;

            if(mini.isEmpty()) return;

            mini.insert(plusparent);
        }     
위의 코드로 트리의 왼쪽은 0, 오른쪽은 1을 할당한 이진코드의 값은 아래의 사진과 같다.     
![162628647-edce3538-ec7d-47ac-941f-d78a86902451](https://user-images.githubusercontent.com/101388345/162677755-3d278a8b-f775-4e1c-a9d1-65384fd4e61c.png)

따라서 이 이진코드의 허프만 트리를 그려보았다.     
![허프만 트리 구현](https://user-images.githubusercontent.com/101388345/162677853-f5705be7-b398-4bba-bac5-f9348ffa5fe0.png)
위 사진과 같이 사용 빈도가 높은 문자가 트리의 앞부분에 있고 낮은 문자가 트리의 제일 밑부분에 있는 것을 알 수 있다.     



# 5. 허프만 코드 성능분석    

허프만 코드에서 성능분석이란 기존의 텍스트 파일에서 압축하고 난 후의 텍스트 파일이 얼마만큼 줄었는가를 나타내는 것이다.   
기존의 alice 텍스트 파일에서의 문자열의 길이가 107748이었고, ASCII 코드로 나타낼 시 8bit를 곱한 861984bit이다.    
허프만 코드에서는 변형된 값이 가변 길이 코드이다. 이진코드의 값에 각각의 빈도수를 곱해서 더했을때 값이 451520이 나왔다.    
451520 / 861984 *100 = 54 정도 이므로 대략 46% 정도 압축되었다. 

앞에서 언급했듯이 허프만 알고리즘은 압축률이 일정한 것이 아니라 값이 변동이 된다.    
또한 위의 트리를 구현했을 때 이진코드가 들어갈 노드들이 앞에 많이 있음에도 불구하고 코드가 길어진 것 처럼, 알고리즘을 짜는 방식에 따라 이진코드의 값을 줄여서 압축률을 높일 수 있을 것이다.


# 6. 보고서를 마치며

이번 허프만 압축 팀 과제를 하기 위해 가장 먼저 한 것은 각자 사용하는 언어가 무엇인지 알아보는 것이었다.   
각자 python, java, c 등 서로 사용하는 언어가 달라 그에 맞춰서 서로 해야할 파트를 나누기로 했다.    
문서 파일을 적용하기 용이한 python을 사용하는 팀원 분이 전처리를 담당하기로 했고, java 수업인만큼 알고리즘은 java를 사용하는 팀원 분이 구현하기로 했다.     
 
과제를 하면서 평소 공부할 때 블로그나 책에 적혀있는 코드를 보고 넘기는 것이 아닌 직접 찾아보고 생각해가며 코드를 구현했다.     
또한 구현한 코드를 실행해서 압축률을 계산하고, 성능분석과 이진트리를 직접 그려보면서 결과를 도출하며 보충할 부분을 다시 생각해볼 수 있었다.    
