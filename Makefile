help:
	@echo "🛠 기본 명령어 목록:"
	@echo "------------------------------------------------"
	@echo "make init-pre-commit     - pre-commit 훅 활성화"
	@echo "make format              - Kotlin 포맷팅"
	@echo "------------------------------------------------"

init-pre-commit:
	@echo "⚙️ pre-commit 훅 활성화"
	@echo "------------------------------------------------"
	git config core.hooksPath .githooks
	chmod +x .githooks/pre-commit
	git update-index --chmod=+x .githooks/pre-commit
	@echo "------------------------------------------------"

format:
	@echo "✨ Kotlin 포맷팅"
	@echo "------------------------------------------------"
	./gradlew formatKotlin
	@echo "------------------------------------------------"
